package com.rongly.zupu.controller.system;

import com.rongly.zupu.common.config.ConfigParent;
import com.rongly.zupu.common.config.RedisConstants;
import com.rongly.zupu.entity.system.ConfigDO;
import com.rongly.zupu.service.system.ConfigService;
import com.rongly.zupu.utils.PageUtils;
import com.rongly.zupu.utils.Query;
import com.rongly.zupu.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置
 * 
 * @date 2017-10-26 17:12:22
 */
 
@Controller
@RequestMapping("/system/config")
public class ConfigController {
	@Autowired
	private ConfigService configService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@GetMapping()
	@RequiresPermissions("system:config:config")
	String Config(Model model){
		model.addAttribute("configParents", ConfigParent.descMap);
	    return "system/config/config";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:config:config")
	public PageUtils list(@RequestParam Map<String, Object> params, Model model){
		//查询列表数据
        Query query = new Query(params);
		List<ConfigDO> configList = configService.list(query);
		int total = configService.count(query);
		PageUtils pageUtils = new PageUtils(configList, total);
		model.addAttribute("configParents", ConfigParent.descMap);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:config:add")
	String add(Model model){
		model.addAttribute("configParents", ConfigParent.descMap);
	    return "system/config/add";
	}

	@GetMapping("/edit/{configItem}")
	@RequiresPermissions("system:config:edit")
	String edit(@PathVariable("configItem") String configItem, Model model){
		ConfigDO config = configService.get(configItem);
		config.setConfigValue((String)stringRedisTemplate.boundHashOps(RedisConstants.cache_config_parent + config.getParentItem()).get(config.getConfigItem()));
		model.addAttribute("config", config);
		model.addAttribute("configParents", ConfigParent.descMap);
	    return "system/config/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:config:add")
	public R save(ConfigDO config){
		if(configService.save(config)!=null){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:config:edit")
	public R update( ConfigDO config){
		configService.update(config);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:config:remove")
	public R remove(@RequestParam("id")Integer id, @RequestParam("configItem")String configItem, @RequestParam("parentItem") String parentItem){
		if(configService.remove(id,configItem,parentItem)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:config:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids, @RequestParam("configItems[]") String[] configItems,
                    @RequestParam("parentItems[]") String[] parentItems ){
		configService.batchRemove(ids,configItems,parentItems);
		return R.ok();
	}
	
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !configService.exit(params);
	}
}
