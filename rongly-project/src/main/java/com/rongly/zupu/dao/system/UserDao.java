package com.rongly.zupu.dao.system;

import com.rongly.zupu.entity.system.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @date 2017-10-03 09:45:11
 */
@Mapper
@Repository
public interface UserDao {

	UserDO get(Integer userId);
	
	UserDO getByUserName(String username);
	
	List<UserDO> list(Map<String, Object> map);

	List<UserDO> listByUserType(@Param("userType") Integer userType);

	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int updatePassword(UserDO user);

	int updateFundPassword(UserDO user);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);
	
	Integer[] listAllDept();

	/**
	 * @Description 通过用户名删除
	 * @param username
	 */
	void removeByUsername(String username);

	/**
	 * @Description 通过用户名批量删除
	 * @param username
	 */
	void batchRemoveByUsername(String[] username);
}
