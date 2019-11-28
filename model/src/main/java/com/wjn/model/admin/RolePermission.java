package com.wjn.model.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description: 角色权限关联表
 * @author: jnWang
 * @create: 2019-11-19 16:13
 */
@Getter
@Setter
@FieldNameConstants
public class RolePermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    private Integer permissionId;

    /**
     * 角色名称
     */
    private Integer roleId;
}
