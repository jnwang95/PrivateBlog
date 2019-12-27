package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.wjn.bean.model.Article;
import com.wjn.bean.model.ArticleBlog;
import com.wjn.bean.model.ArticleBlogDetail;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.mapper.CategoryMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.model.admin.Category;
import com.wjn.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-27 11:35
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BlogContentMapper blogContentMapper;

    @Override
    public List<Article> caegorys() {
        Example example = new Example(Category.class);
        example.selectProperties(Category.Fields.id,Category.Fields.name,Category.Fields.number);
        //包含分类id和name和数量
        List<Category> categories = categoryMapper.selectByExample(example);
        List<Article> articles = new ArrayList<>();
        for (Category category : categories) {
            Article article = new Article();
            BeanUtil.copyProperties(category,article);
            articles.add(article);
        }
        return articles;
    }

    @Override
    public Set<String> labels() {
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.label);
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        Set<String> set = new HashSet<>();
        for (BlogContent blogContent : blogContents) {
            String label = blogContent.getLabel();
            if(StrUtil.isEmpty(label)){
                continue;
            }
            if(label.contains(",")){
                String[] split = label.split(",");
                set.addAll(Arrays.asList(split));
                continue;
            }
            set.add(label);
        }
        return set;
    }

    @Override
    public List<ArticleBlog> showLabel(String label) {
        return articleBlogDetails(label,null);
    }

    @Override
    public List<ArticleBlog> showCategory(Integer id) {
        return articleBlogDetails(null,id);
    }

    @Override
    public ArticleBlogDetail articleBlogDetail(Long id) {
        BlogContent blogContent = blogContentMapper.selectByPrimaryKey(id);
        ArticleBlogDetail articleBlogDetail = new ArticleBlogDetail();
        articleBlogDetail.setTitle(blogContent.getTitle());
        articleBlogDetail.setContent(blogContent.getContent());
        return articleBlogDetail;
    }

    private List<ArticleBlog> articleBlogDetails(String label, Integer id){
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.title,BlogContent.Fields.id);
        if(StrUtil.isNotEmpty(label))example.createCriteria().andLike(BlogContent.Fields.label,"%"+label+"%");
        if(!StringUtils.isEmpty(id))example.createCriteria().andEqualTo(BlogContent.Fields.categoryId,id);
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        List<ArticleBlog> articleBlogs = new LinkedList<>();
        for (BlogContent blogContent : blogContents) {
            ArticleBlog articleBlog = new ArticleBlog();
            BeanUtil.copyProperties(blogContent, articleBlog);
            articleBlogs.add(articleBlog);
        }
        return articleBlogs;
    }
}
