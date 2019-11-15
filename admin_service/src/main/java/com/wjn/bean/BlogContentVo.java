package com.wjn.bean;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.model.admin.BlogContent;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther WJN
 * @date 2019/10/13 0013 下午 8:03
 * @describe
 */
@Data
public class BlogContentVo extends Pagination implements Serializable {
    private Integer id;
    //图片
    private String img;
    //标题
    private String title;
    //副标题
    private String subTitle;
    //内容
    private String content;
    //分类id
    private Integer categoryId;
    //标签
    private String label;
    //标签集
    private List<String> labels;
    //状态
    private Integer state;
    //分类名称
    private String category;
    //添加时间
    private Long entryTime;
    //作者
    private String author;


    public static void conver(BlogContentVo blogContenVo, BlogContent blogContent){
        BeanUtil.copyProperties(blogContenVo,blogContent);
    }
}
