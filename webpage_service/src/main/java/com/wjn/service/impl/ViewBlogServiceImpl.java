package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.bean.dto.BlogContentDto;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.service.ViewBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther WJN
 * @date 2019/10/30 17:59
 * @describetion
 */
@Service
public class ViewBlogServiceImpl implements ViewBlogService {

    @Resource
    private BlogContentMapper blogContentMapper;

    @Override
    public BlogContentDto getContentById(Long id) {
        BlogContent blogContent = blogContentMapper.selectByPrimaryKey(id);
        BlogContentDto blogContentDto = BlogContentDto.of();
        BeanUtil.copyProperties(blogContent,blogContentDto);
        return blogContentDto;
    }
}
