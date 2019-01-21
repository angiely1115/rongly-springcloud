package com.rongly.zupu.entity.system;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
@Data
@TableName(value = "sys_log")
public class LogDO {
	@TableId
	private Long id;

	private Integer userId;

	private String username;

	private String operation;

	private Long time;

	private String method;

	private String params;

	private String ip;
	/**
	 * 逻辑删除 删除为1 未删除0
	 */
	@TableLogic
	private Integer del;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private ZonedDateTime gmtCreate;

}