package com.wjn.mapper;

import com.wjn.model.admin.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @auther WJN
 * @date 2019/9/17 15:33
 * @describetion
 */
public interface MenuMapper extends Mapper<Menu> {

    /**
     * 获取所有启用的目录
     * @return 《map》
     */
    List<Map<String, Object>> getMenu();
}
