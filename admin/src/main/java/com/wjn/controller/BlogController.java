package com.wjn.controller;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.*;
import com.wjn.bean.BlogContentVo;
import com.wjn.bean.CategoryVo;
import com.wjn.bean.Pagination;
import com.wjn.bean.TitleImgVo;
import com.wjn.model.admin.Category;
import com.wjn.service.BlogService;
import com.wjn.config.ConfigConstant;
import com.wjn.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The type Blog controller.
 *
 * @auther WJN
 * @date 2019 /10/13 0013 下午 9:38
 * @describe 文章操作控制器
 */
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private ConfigConstant configConstant;
    @Autowired
    private BlogService blogService;

    /**
     * Upload blog json result.
     *
     * @param file the file
     * @return the json result
     */
    @PostMapping("/upload")
    @RequiresRoles(value = "host")
    public JsonResult uploadBlog(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //获取图片后缀名
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index + 1);
        String fileName = configConstant.getAddress()+UUID.randomUUID().toString().replaceAll("-", "")+"."+suffix;
        try {
            OSS ossClient = new OSSClientBuilder().build(configConstant.getEndPoint(), configConstant.getAccessKey(),
                    configConstant.getSecretKey());
            ossClient.putObject(configConstant.getBucket(),fileName,file.getInputStream());
            ossClient.shutdown();
        } catch (IOException e) {
            return JsonResult.failMessage("上传失败");
        }
        String url = "https://"+configConstant.getBucket()+".oss-cn-shanghai.aliyuncs.com/"+fileName;
        return JsonResult.success(url);
    }

    /**
     * Upload title img json result.
     * 上传默认图片
     * @param file the file
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("/uploadTitleImg")
    public JsonResult uploadTitleImg(@RequestParam("file") MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        //获取图片后缀名
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index + 1);
        String fileName = configConstant.getAddress()+UUID.randomUUID().toString().replaceAll("-", "")+"."+suffix;
        try {
            OSS ossClient = new OSSClientBuilder().build(configConstant.getEndPoint(), configConstant.getAccessKey(),
                    configConstant.getSecretKey());
            ossClient.putObject(configConstant.getBucket(),fileName,file.getInputStream());
            ossClient.shutdown();
        } catch (IOException e) {
            return JsonResult.failMessage("上传失败");
        }
        String url = "https://"+configConstant.getBucket()+".oss-cn-shanghai.aliyuncs.com/"+fileName;
        return JsonResult.success(url);
    }

    /**
     * Insert title img json result.
     * 新增默认图片入库
     * @param url the url
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("insertTitleImg")
    public JsonResult insertTitleImg(String url){
        boolean result = blogService.insertTitleImg(url);
        if(result){
            return JsonResult.successMessage("添加成功！");
        }
        return JsonResult.failMessage("添加失败");
    }

    /**
     * Insert blog json result.
     * 新增博客
     * @param blogContentVo the blog content vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("insertBlog")
    public JsonResult insertBlog(BlogContentVo blogContentVo){
        boolean result = blogService.insertBlog(blogContentVo);
        if(result){
            return JsonResult.successMessage("添加成功！");
        }
        return JsonResult.failMessage("添加失败");
    }

    /**
     * Updata blog json result.
     * 修改博客
     * @param blogContentVo the blog content vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("updataBlog")
    public JsonResult updataBlog(BlogContentVo blogContentVo){
        boolean result = blogService.updataBlog(blogContentVo);
        if(result){
            return JsonResult.successMessage("添加成功！");
        }
        return JsonResult.failMessage("添加失败");
    }

    /**
     * Get category id json result.
     * 获取最新的分类id
     * @return the json result
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getCategoryId")
    public JsonResult getCategoryId(){
        Integer categoryId = blogService.getCategoryId();
        return JsonResult.success(categoryId+1);
    }

    /**
     * Add category json result.
     * 新增分类
     * @param categoryVo the category vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("addCategory")
    public JsonResult addCategory(CategoryVo categoryVo){
        boolean result = blogService.addCategory(categoryVo);
        if(result){
            return JsonResult.successMessage("添加成功！");
        }
        return JsonResult.failMessage("添加失败");
    }

    /**
     * Get categorys json result.
     * 获取分类列表
     * @param categoryVo the category vo
     * @return the json result
     */
    @RequiresRoles(value = {"common"})
    @PostMapping("getCategorys")
    public JsonResult getCategorys(CategoryVo categoryVo){
        Pagination pagination = blogService.getCategorys(categoryVo);
        return JsonResult.success(pagination);
    }

    /**
     * Updata category state json result.
     * 修改分类状态
     * @param categoryVo the category vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("updataCategoryState")
    public JsonResult updataCategoryState(CategoryVo categoryVo){
        boolean result = blogService.updataCategoryState(categoryVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.failMessage("修改失败");
    }

    /**
     * Updata blog state json result.
     * 修改博客状态
     * @param blogContentVo the blog content vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("updataBlogState")
    public JsonResult updataBlogState(BlogContentVo blogContentVo){
        boolean result = blogService.updataBlogState(blogContentVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.failMessage("修改失败");
    }

    /**
     * Delete category json result.
     * 删除分类
     * @param id the id
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @DeleteMapping("deleteCategory")
    public JsonResult deleteCategory(String id){
        boolean result = blogService.deleteCategory(id);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.failMessage("删除失败");
    }

    /**
     * Delete blog json result.
     * 删除博客
     * @param id the id
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @DeleteMapping("deleteBlog")
    public JsonResult deleteBlog(String id){
        boolean result = blogService.deleteBlog(id);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.failMessage("删除失败");
    }

    /**
     * Delete title img by id json result.
     * 删除默认图片
     * @param id the id
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @DeleteMapping("deleteTitleImgById")
    public JsonResult deleteTitleImgById(String id){
        boolean result = blogService.deleteTitleImgById(id);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.failMessage("删除失败");
    }

    /**
     * Gets name by id.
     * 根据id获取名称
     * @param id the id
     * @return the name by id
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getNameById/{id}")
    public JsonResult getNameById(@PathVariable("id") Integer id){
        String name = blogService.getNameById(id);
        return JsonResult.success(name);
    }

    /**
     * Updata category name by id json result.
     * 修改名称
     * @param categoryVo the category vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("updataCategoryNameById")
    public JsonResult updataCategoryNameById(CategoryVo categoryVo){
        if(StrUtil.isEmpty(categoryVo.getName())){
            return JsonResult.failMessage("修改失败");
        }
        if(categoryVo.getId() == null){
            return JsonResult.failMessage("参数异常");
        }
        boolean result = blogService.updataCategoryNameById(categoryVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.failMessage("修改失败");
    }

    /**
     * Batch delete category json result.
     * 批量删除分类
     * @param ids the ids
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("batchDeleteCategory")
    public JsonResult batchDeleteCategory(String ids){
        boolean result = blogService.batchDeleteCategory(ids);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    /**
     * Batch delete blog json result.
     * 批量删除博客
     * @param ids the ids
     * @return the json result
     */
    @PostMapping("batchDeleteBlog")
    @RequiresRoles(value = "host")
    public JsonResult batchDeleteBlog(String ids){
        boolean result = blogService.batchDeleteBlog(ids);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    /**
     * Get category id and name json result.
     * 获取分类id和name
     * @return the json result
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getCategoryIdAndName")
    public JsonResult getCategoryIdAndName(){
        List<Category> categoryIdAndName = blogService.getCategoryIdAndName();
        return JsonResult.success(categoryIdAndName);
    }


    /**
     * Get blogs json result.
     * 获取博客列表
     * @param blogContentVo the blog content vo
     * @return the json result
     */
    @RequiresRoles(value = {"common"})
    @PostMapping("getBlogs")
    public JsonResult getBlogs(BlogContentVo blogContentVo){
        Pagination pagination = blogService.getBlogs(blogContentVo);
        return JsonResult.success(pagination);
    }

    /**
     * Gets blog content by id.
     * 根据id获取博客内容
     * @param id the id
     * @return the blog content by id
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getBlogContentById/{id}")
    public JsonResult getBlogContentById(@PathVariable("id")Integer id){
        String container = blogService.getBlogContentById(id);
        return JsonResult.success(container);
    }

    /**
     * Gets blog by id.
     * 根据id获取博客
     * @param id the id
     * @return the blog by id
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getBlogById/{id}")
    public JsonResult getBlogById(@PathVariable("id")Integer id){
        BlogContentVo blogContentVo = blogService.getBlogById(id);
        return JsonResult.success(blogContentVo);
    }

    /**
     * Get all title img json result.
     * 获取所有默认图片
     * @return the json result
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getAllTitleImg")
    public JsonResult getAllTitleImg(){
        List<TitleImgVo> titleImgVos = blogService.getAllTitleImg();
        return JsonResult.success(titleImgVos);
    }

    /**
     * Get random url json result.
     * 随机获取url
     * @return the json result
     */
    @RequiresRoles(value = {"common"})
    @GetMapping("getRandomUrl")
    public JsonResult getRandomUrl(){
        String url = blogService.getRandomUrl();
        return JsonResult.success(url);
    }

    /**
     * Updata title img state json result.
     * 修改默认图片的状态
     * @param titleImgVo the title img vo
     * @return the json result
     */
    @RequiresRoles(value = "host")
    @PostMapping("updataTitleImgState")
    public JsonResult updataTitleImgState(TitleImgVo titleImgVo){
        boolean result = blogService.updataTitleImgState(titleImgVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

}
