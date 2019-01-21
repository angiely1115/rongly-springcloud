package com.rongly.zupu.entity.system;

import java.io.Serializable;


/**
 * 部门管理
 * 
 * @date 2017-09-27 14:28:36
 */
public class DeptDO implements Serializable {
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	//
	private Integer deptId;
	//上级部门ID，一级部门为0
	private Integer parentId;
	//部门名称
	private String name;
	//排序
	private Integer orderNum;
	//是否删除  0：禁用  1：正常
	private Integer delFlag = 1;

	/**
	 * 设置：
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * 设置：上级部门ID，一级部门为0
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部门ID，一级部门为0
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：是否删除  -1：已删除  0：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  0：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	@Override
	public String toString() {
		return "DeptDO{" +
				"deptId=" + deptId +
				", parentId=" + parentId +
				", name='" + name + '\'' +
				", orderNum=" + orderNum +
				", delFlag=" + delFlag +
				'}';
	}
}
