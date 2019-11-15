package com.wjn.controller;

import com.wjn.blog.familyblog.bean.index.dto.BlogContentDto;
import com.wjn.blog.familyblog.common.utils.JsonResult;
import com.wjn.blog.familyblog.service.view.ViewBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther WJN
 * @date 2019/10/30 17:54
 * @describetion 前台博客内容
 */
@RestController
@RequestMapping("view/blog")
public class ViewBlogController {

    @Autowired
    private ViewBlogService viewBlogService;

    @GetMapping("getContent/{id}")
    public JsonResult getContentById(@PathVariable("id") Long id){
        BlogContentDto blogContentDto = viewBlogService.getContentById(id);
        return JsonResult.success(blogContentDto);
    }

}
