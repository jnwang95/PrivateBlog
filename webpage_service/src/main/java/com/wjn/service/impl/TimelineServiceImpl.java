package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.wjn.bean.dto.TimelineDto;
import com.wjn.mapper.BlogContentMapper;
import com.wjn.model.admin.BlogContent;
import com.wjn.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther WJN
 * @date 2019/12/23 0023 下午 10:14
 * @describe
 */
@Service
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    private BlogContentMapper blogContentMapper;

    @Override
    public List<TimelineDto> getTimeLineList() {
        Example example = new Example(BlogContent.class);
        example.selectProperties(BlogContent.Fields.id,BlogContent.Fields.title,BlogContent.Fields.entryTime);
        example.orderBy(BlogContent.Fields.entryTime).desc();
        List<BlogContent> blogContents = blogContentMapper.selectByExample(example);
        List<TimelineDto> timelineDtos = new LinkedList<>();
        for (BlogContent blogContent : blogContents) {
            TimelineDto timelineDto = new TimelineDto();
            BeanUtil.copyProperties(blogContent,timelineDto);
            Long entryTime = blogContent.getEntryTime();
            DateTime date = DateUtil.date(entryTime);
            String format = DateUtil.format(date, "yyyy-MM-dd");
            timelineDto.setTitleTime(date.toString());
            timelineDto.setSubTime(format);
            timelineDtos.add(timelineDto);
        }
        return timelineDtos;
    }
}
