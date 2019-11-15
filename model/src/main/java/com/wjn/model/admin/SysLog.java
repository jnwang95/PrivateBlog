package com.wjn.model.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther WJN
 * @date 2019/9/08 0001 下午 11:21
 * @describe 系统日志类
 */

@Data
public class SysLog implements Serializable{

	//id
	@ApiModelProperty("主键id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Integer id;
	//账号
	@ApiModelProperty("账号")
	private String username;
	//操作
	@ApiModelProperty("操作")
	private String operation;
	//时间
	@ApiModelProperty("执行时长")
	private Integer time;
	//方法
	@ApiModelProperty("方法")
	private String method;
	//属性
	@ApiModelProperty("属性")
	private String params;
	//ip
	@ApiModelProperty("ip")
	private String ip;
	//创建时间
	@ApiModelProperty("创建时间")
	private Date createTime;

}
