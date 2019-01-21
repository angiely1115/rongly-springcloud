package com.rongly.zupu.service.system.impl;

import com.rongly.zupu.dao.system.RoleDao;
import com.rongly.zupu.dao.system.RoleMenuDao;
import com.rongly.zupu.dao.system.UserDao;
import com.rongly.zupu.dao.system.UserRoleDao;
import com.rongly.zupu.entity.system.RoleDO;
import com.rongly.zupu.entity.system.RoleMenuDO;
import com.rongly.zupu.service.system.RoleService;
import com.vip.vjtools.vjkit.collection.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

	public static final String ROLE_ALL_KEY = "\"role_all\"";

	public static final String DEMO_CACHE_NAME = "role";

	@Autowired
	RoleDao roleMapper;
	@Autowired
	RoleMenuDao roleMenuMapper;
	@Autowired
	UserDao userMapper;
	@Autowired
	UserRoleDao userRoleMapper;

	@Override
	public List<RoleDO> list() {
		List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
		return roles;
	}

	@Override
	public List<RoleDO> list(Integer userId) {
		List<Integer> rolesIds = userRoleMapper.listRoleId(userId);
		List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
		for (RoleDO roleDO : roles) {
			roleDO.setRoleSign("false");
			for (Integer roleId : rolesIds) {
				if (Objects.equals(roleDO.getRoleId(), roleId)) {
					roleDO.setRoleSign("true");
					break;
				}
			}
		}
		return roles;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int save(RoleDO role) {
		int count = roleMapper.save(role);
		if (count>0) {
			List<Integer> menuIds = role.getMenuIds();
			Integer roleId = role.getRoleId();
			List<RoleMenuDO> rms = ListUtil.newArrayListWithCapacity(menuIds.size());
			for (Integer menuId : menuIds) {
				RoleMenuDO rmDo = new RoleMenuDO();
				rmDo.setRoleId(roleId);
				rmDo.setMenuId(menuId);
				rms.add(rmDo);
			}
//		roleMenuMapper.removeByRoleId(roleId);
			if (rms.size() > 0) {
				roleMenuMapper.batchSave(rms);
			}
		}
		return count;
	}

	@CacheEvict(value = DEMO_CACHE_NAME)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int remove(Integer id) {
		int count = roleMapper.remove(id);
		if (count>0) {
			roleMenuMapper.removeByRoleId(id);
		}
		return count;
	}

	@Override
	public RoleDO get(Integer id) {
		RoleDO roleDO = roleMapper.get(id);
		return roleDO;
	}

	@CacheEvict(value = DEMO_CACHE_NAME)
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(RoleDO role) {
		int r = roleMapper.update(role);
		List<Integer> menuIds = role.getMenuIds();
		Integer roleId = role.getRoleId();
		roleMenuMapper.removeByRoleId(roleId);
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Integer menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		// roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return r;
	}

	@Override
	public int batchremove(Integer[] ids) {
		int r = roleMapper.batchRemove(ids);
		roleMenuMapper.batchRemoveByRoles(ids);
		return r;
	}

}
