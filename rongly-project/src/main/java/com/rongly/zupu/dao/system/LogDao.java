package com.rongly.zupu.dao.system;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rongly.zupu.entity.system.LogDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @date 2017-10-03 15:45:42
 */
@Mapper
@Repository
public interface LogDao extends BaseMapper<LogDO> {

	LogDO get(Long id);
	
	List<LogDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(LogDO log);
	
	int update(LogDO log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
