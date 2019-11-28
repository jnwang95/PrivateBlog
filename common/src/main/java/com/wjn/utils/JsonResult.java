package com.wjn.utils;

import lombok.Data;

/**
 * 描述: json格式数据返回对象，使用CustomJsonResultSerializer 来序列化
 * @author : 汪靖宁
 */
@Data
public class JsonResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> JsonResult<T> fail() {
    	JsonResult<T> ret = new JsonResult<>();
    	ret.setCode(JsonReturnCode.FAIL.getCode());
    	ret.setMsg(JsonReturnCode.FAIL.getDesc());
        return ret;
    }


    public static <T>  JsonResult<T> failMessage(String msg) {
	    	JsonResult<T> ret = JsonResult.fail();
            ret.setMsg(msg);
        return ret;
    }

    public static <T>  JsonResult<T> successMessage(String msg) {
	    	JsonResult<T> ret = JsonResult.success();
	    	ret.setMsg(msg);
	    return ret;
    }

    public static <T> JsonResult<T> success() {
    	JsonResult<T> ret = new JsonResult<>();
    	ret.setCode(JsonReturnCode.SUCCESS.getCode());
    	ret.setMsg(JsonReturnCode.SUCCESS.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> success(T data) {
	    	JsonResult<T> ret = JsonResult.success();
	    	ret.setData(data);
        return ret;
    }

}
