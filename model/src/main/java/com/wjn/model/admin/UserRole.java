package com.wjn.model.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @description: 用户角色模型
 * @author: jnWang
 * @create: 2019-11-19 16:08
 */
@Getter
@Setter
@FieldNameConstants
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
