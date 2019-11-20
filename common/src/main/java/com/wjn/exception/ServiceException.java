package com.wjn.exception;

import cn.hutool.core.convert.Convert;
import com.wjn.utils.JsonReturnCode;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private Integer errorCode;

	public ServiceException(int errorCode, String message) {
		this.code = 500;
		this.message = message;
		this.errorCode = errorCode;
	}

	public ServiceException(int errorCode, String message, int code) {
		this.code = code;
		this.message = message;
		this.errorCode = errorCode;
	}

	public ServiceException(String message, int code) {
		this.code = code;
		this.message = message;
	}

	public ServiceException(String message) {
		this.code =500;
		this.message = message;
	}
}
