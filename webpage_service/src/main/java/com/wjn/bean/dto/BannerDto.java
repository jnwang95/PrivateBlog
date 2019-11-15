package com.wjn.bean.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther WJN
 * @date 2019/9/30 16:18
 * @describetion
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class BannerDto {
    //图片路径
    private String url;
    //内容
    private String content;
}
