package com.rongly.zupu.dao.system;

import com.rongly.zupu.entity.system.MenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @date 2017-10-03 09:45:09
 */
@Mapper
@Repository
public interface MenuDao {

	MenuDO get(Integer menuId);
	
	List<MenuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Integer menuId);
	
	int batchRemove(Integer[] menuIds);
	
	List<MenuDO> listMenuByUserId(Integer id);
	
	List<String> listUserPerms(Integer id);
}
