package com.companyxxx.projectname.config;

import com.companyxxx.projectname.exceptions.UniException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: luzj
 * @date: 2019-01-25
 * @description: restful风格的统一异常处理
 * 0 返回ErrorInfo对象
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UniException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, UniException e) {
        ErrorInfo<String> info = new ErrorInfo<>();
        info.message = e.getMessage();
        info.code = ErrorInfo.ERROR;
        info.data = "data";
        info.url = req.getRequestURL().toString();
        return info;
    }
}
