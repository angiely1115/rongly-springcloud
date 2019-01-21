package com.rongly.zupu.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 公告表
 * 
 * @date 2018-03-08 15:17:29
 */
@Data
@TableName(value = "sys_notice")
public class NoticeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@TableId
	private Integer id;
	//标题
	private String title;
	//内容
	private String content;
	//更新时间
	private Date createTime;
	//创建人
	private String creator;
	@TableLogic
	private Integer del;

}
