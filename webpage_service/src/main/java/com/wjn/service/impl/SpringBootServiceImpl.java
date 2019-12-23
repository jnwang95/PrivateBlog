package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.bean.model.SpringBootQT;
import com.wjn.bean.model.SpringbootQTdetail;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.service.SpringBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-13 15:44
 */
@Service
public class SpringBootServiceImpl implements SpringBootService {

    @Autowired
    private BlogContentMapper blogContentMapper;

    @Override
    public List<SpringBootQT> getSpringBootQT() {
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.id,BlogContent.Fields.title);
        example.createCriteria().andEqualTo(BlogContent.Fields.categoryId,20);
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        List<SpringBootQT> springBootQTS = new ArrayList<>();
        for (BlogContent blogContent : blogContents) {
            SpringBootQT springBootQT = new SpringBootQT();
            BeanUtil.copyProperties(blogContent,springBootQT);
            springBootQTS.add(springBootQT);
        }
        return springBootQTS;
    }

    @Override
    public SpringbootQTdetail getSpringBootQTdetail(String id) {
        BlogContent blogContent = blogContentMapper.selectByPrimaryKey(id);
        SpringbootQTdetail springbootQTdetail = new SpringbootQTdetail();
        springbootQTdetail.setContent(blogContent.getContent());
        springbootQTdetail.setTitle(blogContent.getTitle());
        return springbootQTdetail;
    }
}
