package com.rongly.zupu.service.system;

import com.rongly.zupu.entity.system.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

	RoleDO get(Integer id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Integer id);

	List<RoleDO> list(Integer userId);

	int batchremove(Integer[] ids);
}
