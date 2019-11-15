package com.wjn.bean.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther WJN
 * @date 2019/9/22 0022 下午 11:27
 * @describe 博客内容Dto
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class BlogContentDto {

    private Long id;
    //图片
    private String img;
    //标题
    private String title;
    //副标题
    private String subTitle;
    //内容
    private String content;

}
