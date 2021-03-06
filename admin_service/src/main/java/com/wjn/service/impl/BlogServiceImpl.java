package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wjn.bean.BlogContentVo;
import com.wjn.bean.CategoryVo;
import com.wjn.bean.Pagination;
import com.wjn.bean.TitleImgVo;
import com.wjn.constant.NaturalNumber;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.mapper.CategoryMapper;
import com.wjn.mapper.TitleImgMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.model.admin.Category;
import com.wjn.model.admin.TitleImg;
import com.wjn.service.BlogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther WJN
 * @date 2019/10/15 0015 上午 12:04
 * @describe
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BlogContentMapper blogContentMapper;
    @Autowired
    private TitleImgMapper titleImgMapper;

    @Override
    public Integer getCategoryId() {
        Example example = new Example(Category.class);
        example.selectProperties(Category.Fields.id).orderBy(Category.Fields.id).desc();
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(example, new RowBounds(NaturalNumber.zero, NaturalNumber.one));
        if(CollUtil.isEmpty(categories)){
            return NaturalNumber.zero;
        }
        return CollUtil.getFirst(categories).getId();
    }

    @Override
    public boolean addCategory(CategoryVo categoryVo) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryVo,category);
        DateTime time = DateTime.now();
        category.setEntryTime(time.toJdkDate());
        int resultNumber = categoryMapper.insertSelective(category);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public Pagination getCategorys(CategoryVo cate) {
        Pagination<CategoryVo> pagination = new Pagination<>();
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(cate.getId())){
            criteria.andLike(Category.Fields.id,"%"+cate.getId().toString()+"%");
        }
        if(!StringUtils.isEmpty(cate.getName())){
            criteria.andLike(Category.Fields.name,"%"+cate.getName()+"%");
        }
        Page<Object> objects = PageHelper.startPage(cate.getPageNum(), cate.getPageSize());
        List<Category> categories = categoryMapper.selectByExample(example);
        List<CategoryVo> categoryVos = new LinkedList<>();
        if(CollUtil.isEmpty(categories)){
           return  pagination;
        }
        for (Category category : categories) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtil.copyProperties(category,categoryVo);
            categoryVos.add(categoryVo);
        }
        pagination.setTotal(objects.getTotal());
        pagination.setData(categoryVos);
        return pagination;
    }

    @Override
    public boolean updataCategoryState(CategoryVo categoryVo) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryVo,category);
        int resultNumber = categoryMapper.updateByPrimaryKeySelective(category);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public boolean deleteCategory(String id) {
        if(StrUtil.isEmpty(id)){
            return false;
        }
        Integer integer = Convert.toInt(id);
        int number = categoryMapper.deleteByPrimaryKey(integer);
        return number == NaturalNumber.one;
    }

    @Override
    public String getNameById(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(BeanUtil.isEmpty(category)){
            return null;
        }
        return category.getName();
    }

    @Override
    public boolean updataCategoryNameById(CategoryVo categoryVo) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryVo,category);
        int resultNumber = categoryMapper.updateByPrimaryKeySelective(category);
        return resultNumber == NaturalNumber.one;
    }



    @Override
    public boolean batchDeleteCategory(String ids) {
        String[] strings = getIds(ids);
        if(strings.length == NaturalNumber.zero){
            return false;
        }
        for (String str : strings) {
            categoryMapper.deleteByPrimaryKey(str);
        }
        return true;
    }

    @Override
    public boolean insertBlog(BlogContentVo blogContenVo) {
        BlogContent blogContent = new  BlogContent();
        BlogContentVo.conver(blogContenVo,blogContent);
        Integer categoryId = blogContenVo.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Integer number = category.getNumber();
        category.setNumber(++ number);
        categoryMapper.updateByPrimaryKeySelective(category);
        blogContent.setEntryTime(System.currentTimeMillis());
        int resultNumber = blogContentMapper.insertSelective(blogContent);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public List<Category> getCategoryIdAndName() {
        Example example = new Example(Category.class);
        example.selectProperties(Category.Fields.id, Category.Fields.name);
        List<Category> categories = categoryMapper.selectByExample(example);
        if(CollUtil.isEmpty(categories)){
            return null;
        }
        return categories;
    }

    @Override
    public Pagination getBlogs(BlogContentVo blog) {
        Pagination<BlogContentVo> pagination = new Pagination<>();
        Example example = new Example(BlogContent.class);
        example.excludeProperties(BlogContent.Fields.content,BlogContent.Fields.subTitle,BlogContent.Fields.img);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(blog.getCategoryId())){
            criteria.andEqualTo(BlogContent.Fields.categoryId,blog.getCategoryId());
        }
        if(!StringUtils.isEmpty(blog.getState())){
            criteria.andEqualTo(BlogContent.Fields.state,blog.getState());
        }
        if(!StringUtils.isEmpty(blog.getAuthor())){
            criteria.andLike(BlogContent.Fields.author,"%"+blog.getAuthor()+"%");
        }
        Page<Object> objects = PageHelper.startPage(blog.getPageNum(), blog.getPageSize());
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        List<BlogContentVo> blogContentVos = new LinkedList<>();
        if(CollUtil.isEmpty(blogContents)){
            return  pagination;
        }
        for (BlogContent blogContent : blogContents) {
            BlogContentVo blogContentVo = new BlogContentVo();
            BeanUtil.copyProperties(blogContent,blogContentVo);
            String c = null;
            Category category = categoryMapper.selectByPrimaryKey(blogContentVo.getCategoryId());
            if(!BeanUtil.isEmpty(category)){
                c = category.getName();
            }
            if(StrUtil.isNotBlank(blogContentVo.getLabel())){
                List<String> result = Arrays.asList(blogContentVo.getLabel().split(","));
                blogContentVo.setLabels(result);
            }
            blogContentVo.setCategory(c);
            blogContentVos.add(blogContentVo);
        }
        pagination.setTotal(objects.getTotal());
        pagination.setData(blogContentVos);
        return pagination;

    }

    @Override
    public boolean updataBlogState(BlogContentVo blogContentVo) {
        BlogContent blogContent = new BlogContent();
        BlogContentVo.conver(blogContentVo,blogContent);
        int resultNumber = blogContentMapper.updateByPrimaryKeySelective(blogContent);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public String getBlogContentById(Integer id) {
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.content);
        example.createCriteria().andEqualTo(BlogContent.Fields.id,id);
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        if(CollUtil.isEmpty(blogContents)){
            return null;
        }
        return CollUtil.getFirst(blogContents).getContent();
    }

    @Override
    public boolean deleteBlog(String id) {
        if(StrUtil.isEmpty(id)){
            return false;
        }
        Integer integer = Convert.toInt(id);
        int number = blogContentMapper.deleteByPrimaryKey(integer);
        return number == NaturalNumber.one;
    }

    @Override
    public boolean batchDeleteBlog(String ids) {
        String[] strings = getIds(ids);
        if(strings.length == NaturalNumber.zero){
            return false;
        }
        for (String str : strings) {
            blogContentMapper.deleteByPrimaryKey(str);
        }
        return true;
    }

    @Override
    public BlogContentVo getBlogById(Integer id) {
        BlogContent blogContent = blogContentMapper.selectByPrimaryKey(id);
        BlogContentVo blogContentVo = new BlogContentVo();
        BeanUtil.copyProperties(blogContent,blogContentVo);
        Example example = new Example(Category.class);
        example.selectProperties(Category.Fields.name);
        example.createCriteria().andEqualTo(Category.Fields.id,blogContent.getCategoryId());
        List<Category> categories = categoryMapper.selectByExample(example);
        blogContentVo.setCategory(CollUtil.getFirst(categories).getName());
        String label = blogContentVo.getLabel();
        String[] split;
        List<String> list = new LinkedList<>();
        if(label.contains(",")){
            split = label.split(",");
            list.addAll(Arrays.asList(split));
        }else {
            list.add(label);
        }
        blogContentVo.setLabels(list);
        return blogContentVo;
    }

    @Override
    public boolean updataBlog(BlogContentVo blogContentVo) {
        BlogContent blogContent =  new  BlogContent();
        BlogContentVo.conver(blogContentVo,blogContent);
        Example example = new Example(BlogContent.class);
        example.createCriteria().andEqualTo(BlogContent.Fields.id,blogContent.getId());
        int resultNumber = blogContentMapper.updateByExampleSelective(blogContent, example);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public List<TitleImgVo> getAllTitleImg() {
        List<TitleImg> titleImgs = titleImgMapper.selectAll();
        if(CollUtil.isEmpty(titleImgs)){
            return null;
        }
        List<TitleImgVo> list = new LinkedList<>();
        for (TitleImg titleImg : titleImgs) {
            TitleImgVo titleImgVo = new TitleImgVo();
            BeanUtil.copyProperties(titleImg,titleImgVo);
            list.add(titleImgVo);
        }
        return list;
    }



    @Override
    public boolean insertTitleImg(String url) {
        if(url.isEmpty()){
            return false;
        }
        DateTime time = DateTime.now();
        TitleImg titleImg = new TitleImg();
        titleImg.setUrl(url);
        titleImg.setState(NaturalNumber.one);
        titleImg.setEntryTime(time.toJdkDate());
        int resultNumber = titleImgMapper.insert(titleImg);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public boolean deleteTitleImgById(String id) {
        if(id.isEmpty()){
            return false;
        }
        Integer integer = Convert.toInt(id);
        int resultNumber = titleImgMapper.deleteByPrimaryKey(integer);
        return resultNumber == NaturalNumber.one;
    }

    @Override
    public String getRandomUrl() {
        return titleImgMapper.getRandomUrl();
    }

    @Override
    public boolean updataTitleImgState(TitleImgVo titleImgVo) {
        if(StringUtils.isEmpty(titleImgVo.getId()) || StringUtils.isEmpty(titleImgVo.getState())){
            return false;
        }
        TitleImg titleImg = new TitleImg();
        TitleImgVo.conver(titleImgVo,titleImg);
        Example example = new Example(TitleImg.class);
        example.createCriteria().andEqualTo(TitleImg.Fields.id,titleImg.getId());
        int resultNumber = titleImgMapper.updateByExampleSelective(titleImg, example);
        return resultNumber == NaturalNumber.one;
    }

    private String[] getIds(String ids){
        String replace = ids.replace("[", "").replace("]", "").replace("\"", "");
        return replace.split(",");
    }
}
