package com.rongly.zupu.service.system;


import com.rongly.zupu.domain.Tree;
import com.rongly.zupu.entity.system.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {
	
	DeptDO get(Integer deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Integer deptId);
	
	int batchRemove(Integer[] deptIds);

	Tree<DeptDO> getTree();
}
