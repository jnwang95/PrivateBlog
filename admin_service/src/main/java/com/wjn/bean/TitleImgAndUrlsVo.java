package com.wjn.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther WJN
 * @date 2019/10/20 0020 下午 2:51
 * @describe
 */
@Data
public class TitleImgAndUrlsVo implements Serializable {
    private List<TitleImgVo> titleImgVos;
    private List<String> urls;
}
