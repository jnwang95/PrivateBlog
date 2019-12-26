package com.wjn.service;

import com.wjn.bean.dto.BannerDto;
import com.wjn.bean.dto.HeaderDto;
import com.wjn.bean.dto.MenuDto;

import java.util.List;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-19 10:01
 */
public class JcImpl implements Jc {
    @Override
    public List<MenuDto> getMenu() {
        return null;
    }

    @Override
    public HeaderDto getHeader() {
        return null;
    }

    @Override
    public BannerDto getBanner(String html) {
        return null;
    }

    @Override
    public CommonService getCommonService() {
        return null;
    }
}
