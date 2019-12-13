package com.wjn.service;

import com.wjn.bean.model.SpringBootQT;
import com.wjn.bean.model.SpringbootQTdetail;

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
    SpringbootQTdetail getSpringBootQTdetail(String id);
}
