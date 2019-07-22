package com.rongly.zupu.dao.system;

import com.rongly.zupu.entity.system.DeptDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @date 2017-10-03 15:35:39
 */
@Repository
public interface DeptDao {

	DeptDO get(Integer deptId);
	
	List<DeptDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Integer deptId);
	
	int batchRemove(Integer[] deptIds);
	
	List<Integer> listParentDept();
}
