package com.example.exception.handler;

import com.example.response.OperationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: 全局异常处理器
 * @author: vegetableOnlyBecause
 * @date 2022/9/21 15:48
 * @description:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理参数异常
     * @param request 请求信息
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public OperationResult methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("url={}", request.getRequestURI(), e);
        FieldError fieldError = (FieldError) e.getBindingResult().getAllErrors().get(0);
        String message = fieldError.getDefaultMessage();
        return OperationResult.fail(message);
    }

    /**
     * 处理 Exception
     * @param request 请求信息
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public OperationResult exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("url={}", request.getRequestURI(), e);
        return OperationResult.fail("其他错误");
    }

}
