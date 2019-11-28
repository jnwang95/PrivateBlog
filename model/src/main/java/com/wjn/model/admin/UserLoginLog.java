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
public class UserLoginLog implements Serializable{
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 时间
	 */
	private Integer time;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
