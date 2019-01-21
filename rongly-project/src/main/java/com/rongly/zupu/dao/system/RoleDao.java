package com.rongly.zupu.dao.system;

import com.rongly.zupu.entity.system.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @date 2017-10-02 20:24:47
 */
@Mapper
@Repository
public interface RoleDao {

	RoleDO get(Integer roleId);
	
	List<RoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Integer roleId);
	
	int batchRemove(Integer[] roleIds);
}
