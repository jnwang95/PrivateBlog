package com.wjn.service;


import com.wjn.bean.BlogContentVo;
import com.wjn.bean.CategoryVo;
import com.wjn.bean.Pagination;
import com.wjn.bean.TitleImgVo;
import com.wjn.model.admin.Category;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/10/15 0015 上午 12:04
 * @describe
 */
public interface BlogService {

    Integer getCategoryId();

    boolean addCategory(CategoryVo categoryVo);

    Pagination getCategorys(CategoryVo categoryVo);

    boolean updataCategoryState(CategoryVo categoryVo);

    boolean deleteCategory(String id);

    String getNameById(Integer id);

    boolean updataCategoryNameById(CategoryVo categoryVo);

    boolean batchDeleteCategory(String ids);

    boolean insertBlog(BlogContentVo blogContentVo);

    List<Category> getCategoryIdAndName();

    Pagination getBlogs(BlogContentVo blogContentVo);

    boolean updataBlogState(BlogContentVo blogContentVo);

    String getBlogContentById(Integer id);

    boolean deleteBlog(String id);

    boolean batchDeleteBlog(String ids);

    BlogContentVo getBlogById(Integer id);

    boolean updataBlog(BlogContentVo blogContentVo);

    List<TitleImgVo> getAllTitleImg();

    boolean insertTitleImg(String url);

    boolean deleteTitleImgById(String id);

    String getRandomUrl();

    boolean updataTitleImgState(TitleImgVo titleImgVo);
}
