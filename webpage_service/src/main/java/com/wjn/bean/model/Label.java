package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @description: 标签
 * @author: jnWang
 * @create: 2019-12-27 14:14
 */
@Getter
@Setter
@FieldNameConstants
public class Label implements Serializable {

    private String name;
}
