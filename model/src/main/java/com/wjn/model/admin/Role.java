package com.wjn.model.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @description: 角色表
 * @author: jnWang
 * @create: 2019-11-21 14:43
 */
@Getter
@Setter
@FieldNameConstants
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
}
