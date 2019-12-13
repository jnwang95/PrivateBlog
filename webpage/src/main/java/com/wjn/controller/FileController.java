package com.wjn.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wjn.config.ConfigConstant;
import com.wjn.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @description: 文件上传Controller
 * @author: jnWang
 * @create: 2019-12-11 14:15
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private ConfigConstant configConstant;

    @PostMapping("/upload")
    public JsonResult<String> uploadBlog(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //获取图片后缀名
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index + 1);
        String fileName = configConstant.getAddress()+ UUID.randomUUID().toString().replaceAll("-", "")+"."+suffix;
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


@GetMapping("test")
public void test(){
    System.out.println(123);
}

}
