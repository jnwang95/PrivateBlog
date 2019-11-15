package com.wjn.service;

import com.wjn.blog.familyblog.bean.index.dto.BlogContentDto;

/**
 * @auther WJN
 * @date 2019/10/30 17:58
 * @describetion
 */
public interface ViewBlogService {

    BlogContentDto getContentById(Long id);
}
