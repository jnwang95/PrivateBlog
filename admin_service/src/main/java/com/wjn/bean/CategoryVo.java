package com.wjn.bean;

import cn.hutool.core.bean.BeanUtil;

import lombok.Data;

import java.io.Serializable;
import java.util.Locale;

/**
 * @auther WJN
 * @date 2019/10/14 0014 下午 9:39
 * @describe
 */
@Data
public class CategoryVo extends Pagination implements Serializable {
    //id
    private Integer id;
    //名称
    private String name;
    //是否启用(1.启用，0.不启用)
    private Integer state;

    public static void conver(CategoryVo categoryVo, Locale.Category category){
        BeanUtil.copyProperties(categoryVo,category);
    }

}
