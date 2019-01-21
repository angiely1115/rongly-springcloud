package com.rongly.zupu.dao.system;

import com.rongly.zupu.entity.system.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * @date 2017-10-03 11:08:59
 */
@Mapper
@Repository
public interface RoleMenuDao {

	RoleMenuDO get(Integer id);
	
	List<RoleMenuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(RoleMenuDO roleMenu);
	
	int update(RoleMenuDO roleMenu);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 根据角色ID批量删除
	 * @param roleIds
	 * @return
	 */
	int batchRemoveByRoles(Integer[] roleIds);
	
	List<Integer> listMenuIdByRoleId(Integer roleId);
	
	int removeByRoleId(Integer roleId);
	
	int batchSave(List<RoleMenuDO> list);
}
