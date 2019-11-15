package com.wjn.controller;

import com.wjn.blog.familyblog.bean.index.dto.BannerDto;
import com.wjn.blog.familyblog.bean.index.dto.HeaderDto;
import com.wjn.blog.familyblog.bean.index.dto.MenuDto;
import com.wjn.blog.familyblog.common.utils.JsonResult;
import com.wjn.blog.familyblog.service.view.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/9/30 16:47
 * @describetion
 */
@RestController
@Api(tags = "公共接口")
@RequestMapping("common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 获取目录
     * @return 目录
     */
    @GetMapping("menu")
    @ApiOperation(value = "目录菜单")
    private JsonResult menu(){
        List<MenuDto> menu = commonService.getMenu();
        return JsonResult.success(menu);
    }

    /**
     * 获取主题
     * @return 主题
     */
    @GetMapping("header")
    @ApiOperation(value = "主题文字")
    private JsonResult header(){
        HeaderDto title = commonService.getHeader();
        return JsonResult.success(title);
    }

    /**
     * 获取主图及其内容
     * @return 内容
     */
    @GetMapping("banner/{html}")
    @ApiOperation(value = "获取banner内容")
    private JsonResult banner(@PathVariable("html") String html){
        BannerDto banner = commonService.getBanner(html);
        return JsonResult.success(banner);
    }

}
