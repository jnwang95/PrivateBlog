package com.wjn.controller;

import com.wjn.bean.dto.TimelineDto;
import com.wjn.service.TimelineService;
import com.wjn.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/12/23 0023 下午 10:08
 * @describe 时间轴
 */
@RestController
@RequestMapping("timeline")
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping("list")
    public JsonResult<List<TimelineDto>> getTimeLineList(){
        List<TimelineDto> timeLineList = timelineService.getTimeLineList();
        return JsonResult.success(timeLineList);
    }
}
