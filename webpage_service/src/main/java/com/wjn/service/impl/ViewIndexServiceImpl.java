package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wjn.bean.Pagination;
import com.wjn.bean.dto.BlogContentDto;
import com.wjn.constant.GlobalConstant;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.service.ViewIndexService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


/**
 * @auther WJN
 * @date 2019/9/17 15:22
 * @describetion 首页Service
 */
@Service
public class ViewIndexServiceImpl implements ViewIndexService {

    @Resource
    private BlogContentMapper blogContentMapper;

    @Override
    public Pagination<BlogContentDto> getBLogs(Integer pageNum) {
        Pagination<BlogContentDto> pagination = new Pagination<>();
        Example example = new Example(BlogContent.class);
        //example.orderBy("sort").asc();
        example.selectProperties("id","title","subTitle","img");
        example.createCriteria().andEqualTo("state",1);
        Page<Object> objects = PageHelper.startPage(pageNum, GlobalConstant.VIEW_BLOG_NUMBER);
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        List<BlogContentDto> list = new LinkedList<>();
        for (BlogContent blogContent : blogContents) {
            if(blogContent.getSubTitle().length() > 30){
                blogContent.setSubTitle(blogContent.getSubTitle().substring(0, 29) + "...");
            }
            BlogContentDto blogContentDto = BlogContentDto.of();
            BeanUtil.copyProperties(blogContent,blogContentDto);
            list.add(blogContentDto);
        }
        pagination.setPageNum(objects.getPageNum());
        pagination.setTotalPages(objects.getPages());
        pagination.setData(list);
        pagination.setTotal(objects.getTotal());
        return pagination;
    }

}
