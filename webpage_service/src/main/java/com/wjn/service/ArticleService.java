package com.wjn.service;

import com.wjn.bean.model.Article;
import com.wjn.bean.model.ArticleBlog;
import com.wjn.bean.model.ArticleBlogDetail;

import java.util.List;
import java.util.Set;

public interface ArticleService {
    /**
     * 获取分类及其数量
     * @return
     */
    List<Article> caegorys();

    /**
     * 获取标签
     * @return
     */
    Set<String> labels();

    /**
     * 根据标签获取博客主题和id
     * @return
     */
    List<ArticleBlog> showLabel(String label);

    /**
     * 根据分类id获取博客主题和id
     * @param id
     * @return
     */
    List<ArticleBlog> showCategory(Integer id);

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    ArticleBlogDetail articleBlogDetail(Long id);
}
