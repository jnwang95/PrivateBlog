package com.wjn.model.admin;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description: 用户权限模型
 * @author: jnWang
 * @create: 2019-11-19 16:11
 */
@Data
public class UserPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;
}
