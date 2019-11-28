package com.wjn.exception;

import com.wjn.utils.JsonReturnCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String msg;

	public ServiceException(JsonReturnCode jsonReturnCode) {
		this.code = jsonReturnCode.getCode();;
		this.msg = jsonReturnCode.getDesc();
	}

	public ServiceException(String message) {
		this.code =JsonReturnCode.FAIL.getCode();
		this.msg = message;
	}
}
