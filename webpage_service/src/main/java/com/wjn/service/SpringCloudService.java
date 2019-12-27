package com.wjn.service;


import com.wjn.bean.model.SpringCloudDetail;
import com.wjn.bean.model.SpringCloudQT;

import java.util.List;

public interface SpringCloudService {
    /**
     * 获取springCloudQT
     * @return
     */
    List<SpringCloudQT> getSpringCloudQT();

    /**
     * 获取springCloud全套文章
     * @param id
     * @return
     */
    SpringCloudDetail getSpringCloudQTdetail(String id);

    /**
     * 获取springCloud_gitee
     * @return
     */
    SpringCloudDetail getSpringCloudByGitee();
    /**
     * 获取springCloud_github
     * @return
     */
    SpringCloudDetail getSpringCloudByGithub();
}
