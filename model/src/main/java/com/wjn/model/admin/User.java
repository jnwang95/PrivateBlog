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
 * @date 2019/9/1 0001 下午 11:21
 * @describe
 */
@Getter
@Setter
@FieldNameConstants
public class User implements Serializable {
    /**
     * 主键ID,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名字
     */
    private String name;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String tel;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 注册日期
     */
    private Date entryTime;
}
