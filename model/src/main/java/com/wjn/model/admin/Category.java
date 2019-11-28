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
 * @date 2019/10/14 0014 下午 9:37
 * @describe 博客分类表
 */
@Getter
@Setter
@FieldNameConstants
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date entryTime;
    /**
     * 是否启用(只能启用一个 ，1.启用，2.不启用)
     */
    private Integer state;
}
