package com.wjn.controller;

import com.wjn.bean.model.SpringCloudDetail;
import com.wjn.bean.model.SpringCloudQT;
import com.wjn.service.SpringCloudService;
import com.wjn.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: SpringCloud控制器、
 * @author: jnWang
 * @create: 2019-12-13 15:40
 */
@RestController
@RequestMapping("springcloud")
public class SpringCloudController {

@Autowired
private SpringCloudService springCloudService;

    /**
     * springcloud
     */
    @GetMapping("getSpringCloudQT")
    public JsonResult<List<SpringCloudQT>> getSpringCloudQT(){
       List<SpringCloudQT> springCloudQTS = springCloudService.getSpringCloudQT();
       return JsonResult.success(springCloudQTS);
    }

    /**
     * springcloud全套文章详情
     */
    @GetMapping("getSpringCloudQTdetail/{id}")
    public JsonResult<SpringCloudDetail> getSpringCloudQTdetail(@PathVariable("id") String id){
        SpringCloudDetail springCloudQTdetail = springCloudService.getSpringCloudQTdetail(id);
        return JsonResult.success(springCloudQTdetail);
    }

    /**
     * springcloud_gitee
     */
    @GetMapping("getSpringCloudByGitee")
    public JsonResult<SpringCloudDetail> getSpringCloudByGitee(){
        SpringCloudDetail springCloudQTdetail  = springCloudService.getSpringCloudByGitee();
        return JsonResult.success(springCloudQTdetail);
    }

    /**
     * springcloud_github
     */
    @GetMapping("getSpringCloudByGithub")
    public JsonResult<SpringCloudDetail> getSpringCloudByGithub(){
        SpringCloudDetail springCloudQTdetail  = springCloudService.getSpringCloudByGithub();
        return JsonResult.success(springCloudQTdetail);
    }
}
