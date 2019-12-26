package com.wjn.service;

import com.wjn.bean.model.SpringBootQT;
import com.wjn.bean.model.SpringbootDetail;

import java.util.List;

public interface SpringBootService {
    /**
     * 获取springbootQT
     * @return
     */
    List<SpringBootQT> getSpringBootQT();

    /**
     * 获取springboot全套文章
     * @param id
     * @return
     */
    SpringbootDetail getSpringBootQTdetail(String id);

    /**
     * 获取springboot_gitee
     * @return
     */
    SpringbootDetail getSpringBootByGitee();
    /**
     * 获取springboot_github
     * @return
     */
    SpringbootDetail getSpringBootByGithub();
}
