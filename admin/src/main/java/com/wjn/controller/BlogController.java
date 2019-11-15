package com.wjn.controller;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.*;
import com.wjn.bean.BlogContentVo;
import com.wjn.bean.CategoryVo;
import com.wjn.bean.Pagination;
import com.wjn.bean.TitleImgVo;
import com.wjn.model.admin.Category;
import com.wjn.service.BlogService;
import com.wjn.utils.ConfigConstant;
import com.wjn.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @auther WJN
 * @date 2019/10/13 0013 下午 9:38
 * @describe 文章操作控制器
 */
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private ConfigConstant configConstant;
    @Autowired
    private BlogService blogService;

    @PostMapping("/upload")
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

    //上传默认图片
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

    //新增默认图片入库
    @PostMapping("insertTitleImg")
    public JsonResult insertTitleImg(String url){
        boolean result = blogService.insertTitleImg(url);
        if(result){
            return JsonResult.success("添加成功！");
        }
        return JsonResult.fail("添加失败");
    }

    //新增博客
    @PostMapping("insertBlog")
    public JsonResult insertBlog(BlogContentVo blogContentVo){
        boolean result = blogService.insertBlog(blogContentVo);
        if(result){
            return JsonResult.success("添加成功！");
        }
        return JsonResult.fail("添加失败");
    }

    //修改博客
    @PostMapping("updataBlog")
    public JsonResult updataBlog(BlogContentVo blogContentVo){
        boolean result = blogService.updataBlog(blogContentVo);
        if(result){
            return JsonResult.success("添加成功！");
        }
        return JsonResult.fail("添加失败");
    }

    //获取最新的分类id
    @GetMapping("getCategoryId")
    public JsonResult getCategoryId(){
        Integer categoryId = blogService.getCategoryId();
        return JsonResult.success(categoryId+1);
    }
    //新增分类
    @PostMapping("addCategory")
    public JsonResult addCategory(CategoryVo categoryVo){
        boolean result = blogService.addCategory(categoryVo);
        if(result){
            return JsonResult.success("添加成功！");
        }
        return JsonResult.fail("添加失败");
    }
    //获取分类列表
    @PostMapping("getCategorys")
    public JsonResult getCategorys(CategoryVo categoryVo){
        Pagination pagination = blogService.getCategorys(categoryVo);
        return JsonResult.success(pagination);
    }
    //修改分类状态
    @PostMapping("updataCategoryState")
    public JsonResult updataCategoryState(CategoryVo categoryVo){
        boolean result = blogService.updataCategoryState(categoryVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail("修改失败");
    }
    //修改博客状态
    @PostMapping("updataBlogState")
    public JsonResult updataBlogState(BlogContentVo blogContentVo){
        boolean result = blogService.updataBlogState(blogContentVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail("修改失败");
    }
    //删除分类
    @DeleteMapping("deleteCategory")
    public JsonResult deleteCategory(String id){
        boolean result = blogService.deleteCategory(id);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail("删除失败");
    }
    //删除博客
    @DeleteMapping("deleteBlog")
    public JsonResult deleteBlog(String id){
        boolean result = blogService.deleteBlog(id);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail("删除失败");
    }
    //删除默认图片
    @DeleteMapping("deleteTitleImgById")
    public JsonResult deleteTitleImgById(String id){
        boolean result = blogService.deleteTitleImgById(id);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail("删除失败");
    }
    //根据id获取名称
    @GetMapping("getNameById/{id}")
    public JsonResult getNameById(@PathVariable("id") Integer id){
        String name = blogService.getNameById(id);
        return JsonResult.success(name);
    }
    //修改名称
    @PostMapping("updataCategoryNameById")
    public JsonResult updataCategoryNameById(CategoryVo categoryVo){
        if(StrUtil.isEmpty(categoryVo.getName())){
            return JsonResult.fail("修改失败");
        }
        if(categoryVo.getId() == null){
            return JsonResult.fail("参数异常");
        }
        boolean result = blogService.updataCategoryNameById(categoryVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail("修改失败");
    }
    //批量删除分类
    @PostMapping("batchDeleteCategory")
    public JsonResult batchDeleteCategory(String ids){
        boolean result = blogService.batchDeleteCategory(ids);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    //批量删除博客
    @PostMapping("batchDeleteBlog")
    public JsonResult batchDeleteBlog(String ids){
        boolean result = blogService.batchDeleteBlog(ids);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    //获取分类id和name
    @GetMapping("getCategoryIdAndName")
    public JsonResult getCategoryIdAndName(){
        List<Category> categoryIdAndName = blogService.getCategoryIdAndName();
        return JsonResult.success(categoryIdAndName);
    }
    //获取博客列表
    @PostMapping("getBlogs")
    public JsonResult getBlogs(BlogContentVo blogContentVo){
        Pagination pagination = blogService.getBlogs(blogContentVo);
        return JsonResult.success(pagination);
    }
    //根据id获取博客内容
    @GetMapping("getBlogContentById/{id}")
    public JsonResult getBlogContentById(@PathVariable("id")Integer id){
        String container = blogService.getBlogContentById(id);
        return JsonResult.success(container);
    }
    //根据id获取博客
    @GetMapping("getBlogById/{id}")
    public JsonResult getBlogById(@PathVariable("id")Integer id){
        BlogContentVo blogContentVo = blogService.getBlogById(id);
        return JsonResult.success(blogContentVo);
    }
    //获取所有默认图片
    @GetMapping("getAllTitleImg")
    public JsonResult getAllTitleImg(){
        List<TitleImgVo> titleImgVos = blogService.getAllTitleImg();
        return JsonResult.success(titleImgVos);
    }
    //随机获取url
    @GetMapping("getRandomUrl")
    public JsonResult getRandomUrl(){
        String url = blogService.getRandomUrl();
        return JsonResult.success(url);
    }
    //修改默认图片的状态
    @PostMapping("updataTitleImgState")
    public JsonResult updataTitleImgState(TitleImgVo titleImgVo){
        boolean result = blogService.updataTitleImgState(titleImgVo);
        if(result){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

}
