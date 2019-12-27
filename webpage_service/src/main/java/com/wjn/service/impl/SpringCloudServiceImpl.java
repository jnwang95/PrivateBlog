package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.wjn.bean.config.SpringCloudConfig;
import com.wjn.bean.model.SpringCloudDetail;
import com.wjn.bean.model.SpringCloudQT;
import com.wjn.constant.NaturalNumber;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.service.SpringCloudService;
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
public class SpringCloudServiceImpl implements SpringCloudService {

    @Autowired
    private BlogContentMapper blogContentMapper;

    @Override
    public List<SpringCloudQT> getSpringCloudQT() {
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.id,BlogContent.Fields.title);
        example.createCriteria().andEqualTo(BlogContent.Fields.categoryId,21);
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        List<SpringCloudQT> springCloudQTS = new ArrayList<>();
        for (BlogContent blogContent : blogContents) {
            SpringCloudQT springCloudQT = new SpringCloudQT();
            BeanUtil.copyProperties(blogContent,springCloudQT);
            springCloudQTS.add(springCloudQT);
        }
        return springCloudQTS;
    }

    @Override
    public SpringCloudDetail getSpringCloudQTdetail(String id) {
        BlogContent blogContent = blogContentMapper.selectByPrimaryKey(id);
        SpringCloudDetail springClouddetail = new SpringCloudDetail();
        springClouddetail.setContent(blogContent.getContent());
        springClouddetail.setTitle(blogContent.getTitle());
        return springClouddetail;
    }

    @Override
    public SpringCloudDetail getSpringCloudByGitee() {
        return getSpringCloudDetail(SpringCloudConfig.SPRINGCLOUD_GITEE);
    }

    @Override
    public SpringCloudDetail getSpringCloudByGithub() {
       return getSpringCloudDetail(SpringCloudConfig.SPRINGCLOUD_GITHUB);
    }

    private SpringCloudDetail getSpringCloudDetail(String str){
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.title,BlogContent.Fields.content);
        example.createCriteria().andEqualTo(BlogContent.Fields.title,str);
        List<BlogContent> blogContents = blogContentMapper.selectByExampleAndRowBounds(example, new RowBounds(NaturalNumber.zero, NaturalNumber.one));
        BlogContent blogContent = CollUtil.getFirst(blogContents);
        SpringCloudDetail springCloudDetail = new SpringCloudDetail();
        BeanUtil.copyProperties(blogContent,springCloudDetail);
        return springCloudDetail;
    }
}
