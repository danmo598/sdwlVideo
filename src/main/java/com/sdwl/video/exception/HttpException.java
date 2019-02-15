package com.sdwl.video.exception;

/**
 */
public class HttpException extends  Exception {

    private Integer Httpstatus;


    private String message;


    public HttpException() {
    }

    public HttpException(Integer httpstatus, String message) {
        Httpstatus = httpstatus;
        this.message = message;
    }

    public Integer getHttpstatus() {
        return Httpstatus;
    }

    public void setHttpstatus(Integer httpstatus) {
        Httpstatus = httpstatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
