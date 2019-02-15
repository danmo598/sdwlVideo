package com.sdwl.video.config;


import com.alibaba.fastjson.JSONException;
import com.sdwl.video.exception.BaseException;
import com.sdwl.video.exception.HttpException;
import com.sdwl.video.util.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 全局统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response defaultErrorHandler(HttpServletRequest req, Exception e, HttpServletResponse response) {
        if(e instanceof BaseException) {
            return new Response(((BaseException) e).getStatEnum().key(), ((BaseException) e).getStatEnum().desc(), new Date(), req.getRequestURI());
        }else if(e instanceof MethodArgumentNotValidException || e instanceof JSONException || e instanceof MissingServletRequestParameterException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new Response(HttpStatus.BAD_REQUEST.value(),e.getLocalizedMessage(),new Date(),req.getRequestURI());
        }else if(e instanceof HttpException){
            response.setStatus(((HttpException) e).getHttpstatus());
            return new Response(((HttpException) e).getHttpstatus(),e.getMessage(),new Date(),req.getRequestURI());
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            return new Response(HttpStatus.METHOD_NOT_ALLOWED.value(),e.getMessage(),new Date(),req.getRequestURI());
        } else {
            logger.error("未知异常",e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),new Date(),req.getRequestURI());
        }
    }
}
