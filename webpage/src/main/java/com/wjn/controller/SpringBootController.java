package com.wjn.controller;

import com.wjn.bean.model.SpringBootQT;
import com.wjn.bean.model.SpringBootDetail;
import com.wjn.service.SpringBootService;
import com.wjn.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: SpringBoot控制器、
 * @author: jnWang
 * @create: 2019-12-13 15:40
 */
@RestController
@RequestMapping("springboot")
public class SpringBootController {

@Autowired
private SpringBootService springBootService;

    /**
     * springboot全套列表
     */
    @GetMapping("getSpringBootQT")
    public JsonResult<List<SpringBootQT>> getSpringBootQT(){
       List<SpringBootQT> springBootQTS = springBootService.getSpringBootQT();
       return JsonResult.success(springBootQTS);
    }

    /**
     * springboot全套文章详情
     */
    @GetMapping("getSpringBootQTdetail/{id}")
    public JsonResult<SpringBootDetail> getSpringBootQTdetail(@PathVariable("id") String id){
        SpringBootDetail springBootQTdetail = springBootService.getSpringBootQTdetail(id);
        return JsonResult.success(springBootQTdetail);
    }

    /**
     * springboot_gitee
     */
    @GetMapping("getSpringBootByGitee")
    public JsonResult<SpringBootDetail> getSpringBootByGitee(){
        SpringBootDetail springBootQTdetail  = springBootService.getSpringBootByGitee();
        return JsonResult.success(springBootQTdetail);
    }

    /**
     * springboot_github
     */
    @GetMapping("getSpringBootByGithub")
    public JsonResult<SpringBootDetail> getSpringBootByGithub(){
        SpringBootDetail springBootQTdetail  = springBootService.getSpringBootByGithub();
        return JsonResult.success(springBootQTdetail);
    }
}
