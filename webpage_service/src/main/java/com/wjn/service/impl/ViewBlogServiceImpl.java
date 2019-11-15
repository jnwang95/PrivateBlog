package com.wjn.service.impl;

import com.wjn.blog.familyblog.bean.index.dto.BlogContentDto;
import com.wjn.blog.familyblog.mapper.BlogContentMapper;
import com.wjn.blog.familyblog.model.BlogContent;
import com.wjn.blog.familyblog.service.view.ViewBlogService;
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
        BlogContent.conver(blogContent,blogContentDto);
        return blogContentDto;
    }
}
