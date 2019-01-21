package com.rongly.zupu.dao.system;

import com.rongly.zupu.entity.system.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @date 2017-10-03 11:08:59
 */
@Mapper
@Repository
public interface UserRoleDao {

	UserRoleDO get(Integer id);

	List<UserRoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRoleDO userRole);

	int update(UserRoleDO userRole);

	int remove(Integer id);

	int batchRemove(Integer[] ids);

	List<Integer> listRoleId(Integer userId);

	int removeByUserId(Integer userId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Integer[] ids);

	/**
	 * @Description 删除用户 根据用户名
	 * @param username
	 */
	int removeByUsername(String username);
	
	/**
	 * @Description 批量删除用户 根据用户名
	 * @param usernames
	 */
	int batchRemoveByUsername(String[] usernames);
}
