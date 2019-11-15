package com.wjn.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/9/17 15:44
 * @describetion
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class MenuDto implements Serializable {

    //名称
    @ApiModelProperty(value = "目录名称")
    private String menuName;
    //网址
    @ApiModelProperty(value = "目录网址")
    private String url;
}
