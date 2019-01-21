package com.rongly.zupu.service.system;

import com.rongly.zupu.domain.Tree;
import com.rongly.zupu.entity.system.MenuDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MenuService {
	Tree<MenuDO> getSysMenuTree(Integer id);

	List<Tree<MenuDO>> listMenuTree(Integer id);

	Tree<MenuDO> getTree();

	Tree<MenuDO> getTree(Integer id);

	List<MenuDO> list();

	int remove(Integer id);

	int save(MenuDO menu);

	int update(MenuDO menu);

	MenuDO get(Integer id);

	Set<String> listPerms(Integer userId);
}
