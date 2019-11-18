package com.wjn.service;


import com.wjn.bean.dto.BannerDto;
import com.wjn.bean.dto.HeaderDto;
import com.wjn.bean.dto.MenuDto;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/9/30 16:49
 * @describetion
 */
public interface CommonService {

    /**
     * 获取目录
     * @return 目录集合
     */
    List<MenuDto> getMenu();

    /**
     * 获取主题
     * @return 主题
     */
    HeaderDto getHeader();

    /**
     * 获取主图及其内容
     * @return
     */
    BannerDto getBanner(String html);
}
