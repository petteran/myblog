package com.petter.myblog.common.exception;


import com.petter.myblog.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName GlobalExceptionHandler
 * @DescClass TODO
 * @CreateTime 2021/3/19 19:51
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        log.error("运行时异常---------{}", e);
        return Result.fail(401,e.getMessage(),null);
    }

}
