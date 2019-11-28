package com.wjn.model.admin;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/9/22 0022 下午 11:25
 * @describe 博客内容表
 */
@Getter
@Setter
@FieldNameConstants
public class BlogContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 图片
     */
    private String img;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 分类
     */
    private Integer categoryId;
    /**
     * 标签
     */
    private String label;
    /**
     * 内容
     */
    private String content;
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
    /**
     * 作者
     */
    private String author;

}
