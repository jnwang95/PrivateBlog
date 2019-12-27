package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @description: Article博客详情
 * @author: jnWang
 * @create: 2019-12-27 15:48
 */
@Setter
@Getter
@FieldNameConstants
public class ArticleBlogDetail implements Serializable {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
}
