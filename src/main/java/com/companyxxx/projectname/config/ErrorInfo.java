package com.companyxxx.projectname.config;

/**
 * @author: luzj
 * @date: 2019-01-25
 * @description: 统一异常处理
 */
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    public Integer code;//消息类型
    public String message;//消息内容
    public String url;
    public T data;
}
