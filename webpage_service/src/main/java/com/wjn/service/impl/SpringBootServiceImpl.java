package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.wjn.bean.config.SpringbootConfig;
import com.wjn.bean.model.SpringBootQT;
import com.wjn.bean.model.SpringbootDetail;
import com.wjn.constant.NaturalNumber;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.service.SpringBootService;
import org.apache.ibatis.session.RowBounds;
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
    public SpringbootDetail getSpringBootQTdetail(String id) {
        BlogContent blogContent = blogContentMapper.selectByPrimaryKey(id);
        SpringbootDetail springbootdetail = new SpringbootDetail();
        springbootdetail.setContent(blogContent.getContent());
        springbootdetail.setTitle(blogContent.getTitle());
        return springbootdetail;
    }

    @Override
    public SpringbootDetail getSpringBootByGitee() {
        return getSpringbootDetail(SpringbootConfig.SPRINGBOOT_GITEE);
    }

    @Override
    public SpringbootDetail getSpringBootByGithub() {
       return getSpringbootDetail(SpringbootConfig.SPRINGBOOT_GITHUB);
    }

    private SpringbootDetail getSpringbootDetail(String str){
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.title,BlogContent.Fields.content);
        example.createCriteria().andEqualTo(BlogContent.Fields.title,str);
        List<BlogContent> blogContents = blogContentMapper.selectByExampleAndRowBounds(example, new RowBounds(NaturalNumber.zero, NaturalNumber.one));
        BlogContent blogContent = CollUtil.getFirst(blogContents);
        SpringbootDetail springbootDetail = new SpringbootDetail();
        BeanUtil.copyProperties(blogContent,springbootDetail);
        return springbootDetail;
    }
}
