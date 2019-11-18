package com.wjn.controller;

import com.wjn.bean.Pagination;
import com.wjn.bean.dto.BlogContentDto;
import com.wjn.service.ViewIndexService;
import com.wjn.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther WJN
 * @date 2019/9/17 15:19
 * @describetion 首页控制器
 */
@RestController
@RequestMapping("index")
public class ViewIndexController {
    @Autowired
    private ViewIndexService viewIndexService;

    /**
     * 获取博客标题内容
     * @return
     */
    @GetMapping("getBLogs/{pageNum}")
    @ApiOperation(value = "博客标题内容")
    public JsonResult getBLogs(@PathVariable("pageNum") Integer pageNum){
        Pagination<BlogContentDto> blogContentDtoList = viewIndexService.getBLogs(pageNum);
        return JsonResult.success(blogContentDtoList);
    }


}
