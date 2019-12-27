package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-27 11:35
 */

@Getter
@Setter
@FieldNameConstants
public class Article implements Serializable {

    private Integer id;

    private String name;

    private Integer number;
}
