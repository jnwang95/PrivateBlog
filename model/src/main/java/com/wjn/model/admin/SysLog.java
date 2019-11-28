package com.wjn.model.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

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

@Getter
@Setter
@FieldNameConstants
public class SysLog implements Serializable{
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 时间
	 */
	private Integer time;
	/**
	 * 方法
	 */
	private String method;
	/**
	 * 属性
	 */
	private String params;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
