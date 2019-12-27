package com.wjn.controller;

import com.wjn.bean.model.Article;
import com.wjn.bean.model.ArticleBlog;
import com.wjn.bean.model.ArticleBlogDetail;
import com.wjn.service.ArticleService;
import com.wjn.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @description: 文章控制器（分类，标签）
 * @author: jnWang
 * @create: 2019-12-27 11:33
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取分类及其数量
     * @return
     */
    @GetMapping("caegorys")
    public JsonResult<List<Article>> caegorys(){
      List<Article> articles = articleService.caegorys();
        return JsonResult.success(articles);
    }

    /**
     * 获取标签
     * @return
     */
    @GetMapping("labels")
    public JsonResult<Set<String>> labels(){
        Set<String> labels = articleService.labels();
        return JsonResult.success(labels);
    }

    /**
     * 根据标签获取博客主题和id
     * @return
     */
    @GetMapping("showLabel/{label}")
    public JsonResult<List<ArticleBlog>> labshowLabelels(@PathVariable("label") String label){
        List<ArticleBlog> articleBlogs = articleService.showLabel(label);
        return JsonResult.success(articleBlogs);
    }

    /**
     * 根据分类id获取博客主题和id
     * @return
     */
    @GetMapping("showCategory/{id}")
    public JsonResult<List<ArticleBlog>> showCategory(@PathVariable("id") Integer id){
        List<ArticleBlog> articleBlogs = articleService.showCategory(id);
        return JsonResult.success(articleBlogs);
    }

    /**
     * 根据id获取博客
     * @return
     */
    @GetMapping("articleBlogDetail/{id}")
    public JsonResult<ArticleBlogDetail> articleBlogDetail(@PathVariable("id") Long id){
        ArticleBlogDetail articleBlogDetail = articleService.articleBlogDetail(id);
        return JsonResult.success(articleBlogDetail);
    }
}
