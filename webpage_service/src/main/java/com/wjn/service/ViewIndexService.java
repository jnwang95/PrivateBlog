package com.wjn.service;

import com.wjn.blog.familyblog.bean.index.dto.*;
import com.wjn.blog.familyblog.bean.page.Pagination;

/**
 * @auther WJN
 * @date 2019/9/17 15:22
 * @describetion
 */
public interface ViewIndexService {

    /**
     * 获取博客标题内容
     * @return
     */
    Pagination<BlogContentDto> getBLogs(Integer pageNum);

}
