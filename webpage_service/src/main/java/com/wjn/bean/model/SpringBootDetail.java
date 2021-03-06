package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: springboot详情
 * @author: jnWang
 * @create: 2019-12-13 18:24
 */
@Getter
@Setter
public class SpringBootDetail implements Serializable {

    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
}
