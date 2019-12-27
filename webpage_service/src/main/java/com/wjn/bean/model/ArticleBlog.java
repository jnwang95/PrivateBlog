package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @description: 博客主题和id
 * @author: jnWang
 * @create: 2019-12-27 14:48
 */
@Getter
@Setter
@FieldNameConstants
public class ArticleBlog implements Serializable {

    private Long id;

    private String title;
}
