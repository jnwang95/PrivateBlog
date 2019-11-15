package com.wjn.mapper;

import com.wjn.model.admin.TitleImg;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @auther WJN
 * @date 2019/9/30 16:17
 * @describetion
 */
public interface TitleImgMapper extends Mapper<TitleImg> {

    @Select("select url from title_img where state = 1 ORDER BY RAND() LIMIT 1")
    String getRandomUrl();

}
