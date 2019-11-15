package com.wjn.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @auther WJN
 * @date 2019/10/16 13:43
 * @describetion
 */
@Data
public class Pagination<T> implements Serializable {

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

    private List<T> data;

    private int totalPages;
}
