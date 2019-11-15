package com.wjn.model.admin;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/9/29 13:43
 * @describetion
 */
@Data
public class Test2 implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Long id;
    //账号
    private String name;

}
