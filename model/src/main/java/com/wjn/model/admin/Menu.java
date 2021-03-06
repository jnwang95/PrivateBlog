package com.wjn.model.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/9/17 15:24
 * @describetion
 */
@Getter
@Setter
@FieldNameConstants
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 名称
     */
    private String menuName;
    /**
     * 网址
     */
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * 添加时间
     */
    private Long entryTime;
    /**
     * 启用状态
     */
    private Integer state;
    /**
     * 排序
     */
    private Integer sort;
}