package com.rongly.zupu.service.system.impl;

import com.rongly.zupu.common.config.CfgKeyConst;
import com.rongly.zupu.common.config.RedisConstants;
import com.rongly.zupu.dao.system.ConfigDao;
import com.rongly.zupu.entity.system.ConfigDO;
import com.rongly.zupu.service.system.ConfigService;
import com.rongly.zupu.utils.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public ConfigDO get(String configItem) {
    	ConfigDO config = (ConfigDO) redisTemplate.opsForHash().get(RedisConstants.cache_config,
    			configItem);
		if(config == null){
			config =  configDao.getByItem(configItem);
            redisTemplate.opsForHash().put(RedisConstants.cache_config, configItem, config);
		}
		return config;
    }

    @Override
    public List<ConfigDO> list(Map<String, Object> map) {
        return configDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return configDao.count(map);
    }

    @Override
    public ConfigDO save(ConfigDO config) {
        syncConfig(config, false);
        configDao.save(config);
        redisTemplate.opsForHash().put(RedisConstants.cache_config, config.getConfigItem(), config);
        return config;
    }

    @Override
    public ConfigDO update(ConfigDO config) {
        
        String configValue = config.getConfigValue();
    	if(config.getConfigItem().equals(CfgKeyConst.platformPrivateKey) || config.getConfigItem().equals(CfgKeyConst.platformPublicKey)) {
    		config.setConfigValue("");
        }
        syncConfig(config, false);
        configDao.update(config);
        config.setConfigValue(configValue);
        redisTemplate.opsForHash().put(RedisConstants.cache_config, config.getConfigItem(), config);
  /*      if(config.getConfigItem().equals(CfgKeyConst.platformPrivateKey)) {
    		QhPayUtil.setQhPrivateKey(configValue);
        }else if(config.getConfigItem().equals(CfgKeyConst.platformPublicKey)) {
        	QhPayUtil.setQhPublicKey(configValue);
        }else if(config.getConfigItem().equals(CfgKeyConst.merchNoPrefix)) {
        	QhPayUtil.setMerchNoPrefix(configValue);
        }else if(config.getConfigItem().equals(CfgKeyConst.agentNoPrefix)) {
        	QhPayUtil.setAgentNoPrefix(configValue);
        }*/
        return config;
    }


    /*
     * <p>Title: batchRemove</p> <p>Description: </p>
     * @param ids
     * @param configItems
     * @param parentItems
     * @see com.qh.system.service.ConfigService#batchRemove(java.lang.Integer[], java.lang.String[])
     */

    @Override
    public void batchRemove(Integer[] ids, String[] configItems, String[] parentItems) {
        int result = configDao.batchRemove(ids);
        if (result > 0) {
            for (int i = 0; i < ids.length; i++) {
                redisTemplate.opsForHash().delete(RedisConstants.cache_config, configItems[i]);
                delConfig(configItems[i], parentItems[i]);
            }
        }
    }

    /*
     * <p>Title: remove</p> <p>Description: </p>
     * @param id
     * @param cofigItem
     * @return
     */
    @Override
    public int remove(Integer id, String configItem, String parentItem) {
        int result = configDao.remove(id);
        if (result > 0) {
            redisTemplate.opsForHash().delete(RedisConstants.cache_config, configItem);
           delConfig(configItem, parentItem);
        }
        return result;
    }

    /*
     * <p>Title: exit</p> <p>Description: </p>
     * @param params
     * @return
     */

    @Override
    public boolean exit(Map<String, Object> params) {
        return configDao.list(params).size() > 0;
    }

    private void syncConfig(ConfigDO config, boolean delateFlag) {
        if (config == null) {
            return;
        }
        if (ParamUtil.isNotEmpty(config.getParentItem())) {
            if (delateFlag) {
                redisTemplate.boundHashOps(RedisConstants.cache_config_parent + config.getParentItem()).delete(config.getConfigItem());
            } else {
                redisTemplate.boundHashOps(RedisConstants.cache_config_parent + config.getParentItem()).put(config.getConfigItem(), config.getConfigValue());
            }
        }
    }

    public  void delConfig(String configItem, String parentItem) {
        if (ParamUtil.isNotEmpty(parentItem)) {
            redisTemplate.boundHashOps(RedisConstants.cache_config_parent + parentItem).delete(configItem);
        }
    }

}
