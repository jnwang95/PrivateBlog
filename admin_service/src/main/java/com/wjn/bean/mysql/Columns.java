package com.wjn.bean.mysql;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther WJN
 * @date 2019/9/27 14:11
 * @describetion 数据库的列
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Columns {
//列名
    private String COLUMN_NAME;
//数据结构
    private String DATA_TYPE;
//列属性
    private String COLUMN_TYPE;
//默认值
    private String COLUMN_DEFAULT;
//是否为空
    private Boolean IS_NULLABLE;
//是否为主键
    private Boolean COLUMN_KEY;
//是否自增
    private Boolean EXTRA;
//注释
    private String COLUMN_COMMENT;
}
