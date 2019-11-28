package com.wjn.workbook.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-11-25 15:21
 */
@Data
public class WjnLoveXyq implements Serializable {

    @Excel(name = "男朋友",width = 20,orderNum = "1",isImportField = "wjn")
    private String wjn;
    @Excel(name = "女朋友",width = 20,orderNum = "2",isImportField = "xyq")
    private String xyq;
    @Excel(name = "描述",width = 20,orderNum = "3",isImportField = "love")
    private String love;
}
