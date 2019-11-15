package com.wjn.exception;

import com.wjn.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常接管
 *
 * @author wjn
 * @param
 * @since
 */
@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    /**
     * 基本异常
     */
    @ExceptionHandler(Exception.class)
    public JsonResult exception(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail("Error");
    }

    /**
     * 404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult noHandlerFoundException() {
        return JsonResult.http404("Not found");
    }

    /**
     * 服务异常
     */
    @ExceptionHandler(ServiceException.class)
    public JsonResult serviceException(ServiceException e) {
        return JsonResult.fail(e.getMessage());
    }
}