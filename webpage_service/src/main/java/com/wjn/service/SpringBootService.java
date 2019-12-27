package com.wjn.service;

import com.wjn.bean.model.SpringBootQT;
import com.wjn.bean.model.SpringBootDetail;

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
    SpringBootDetail getSpringBootQTdetail(String id);

    /**
     * 获取springboot_gitee
     * @return
     */
    SpringBootDetail getSpringBootByGitee();
    /**
     * 获取springboot_github
     * @return
     */
    SpringBootDetail getSpringBootByGithub();
}
