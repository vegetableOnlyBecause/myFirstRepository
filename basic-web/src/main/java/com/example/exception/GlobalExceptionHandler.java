package com.example.exception;

import com.example.response.OperationResult;
import lombok.extern.slf4j.Slf4j;
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
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 Exception
     * @param request 请求信息
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public OperationResult exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("服务错误:", e);
        return OperationResult.fail("其他错误", request.getRequestURI());
    }

//    /**
//     * 处理 BusinessException 异常
//     *
//     * @param httpServletRequest httpServletRequest
//     * @param e                  异常
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(value = BusinessException.class)
//    public ResponseEntity businessExceptionHandler(HttpServletRequest httpServletRequest, BusinessException e) {
//        log.info("业务异常。code:" + e.getCode() + "msg:" + e.getMsg());
//        return new ResponseEntity(e.getCode(), e.getMsg());
//    }
}
