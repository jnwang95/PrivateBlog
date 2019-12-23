package com.wjn.service;

import com.wjn.bean.dto.TimelineDto;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/12/23 0023 下午 10:13
 * @describe
 */
public interface TimelineService {
    /**
     * 获取时间轴列表
     */
    List<TimelineDto> getTimeLineList();
}
