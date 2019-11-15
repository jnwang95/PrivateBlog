package com.wjn.bean;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.model.admin.TitleImg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/10/20 0020 下午 2:51
 * @describe
 */
@Data
public class TitleImgVo implements Serializable {
    //主键ID
    @ApiModelProperty("主键id")
    private Integer id;
    //地址
    @ApiModelProperty("地址")
    private String url;
    //状态
    @ApiModelProperty("状态")
    private Integer state;

    public static void conver(TitleImgVo titleImgVo, TitleImg titleImg) {
        BeanUtil.copyProperties(titleImgVo, titleImg);
    }
}
