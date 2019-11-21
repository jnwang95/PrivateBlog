package com.wjn.model.admin;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description: 用户角色模型
 * @author: jnWang
 * @create: 2019-11-19 16:08
 */
@Data
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 用户Id
     */
    private Long userId;
}
