package com.sdwl.video.exception;


import com.sdwl.video.util.enums.StatEnum;

/**
 */
public class BaseException extends Exception {


   private StatEnum statEnum;


    private Object object;


    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(StatEnum statEnum) {
        super();
        this.statEnum = statEnum;
    }

    public BaseException(StatEnum statEnum, Object object) {
        super();
        this.statEnum = statEnum;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public StatEnum getStatEnum() {
        return statEnum;
    }

    public void setStatEnum(StatEnum statEnum) {
        this.statEnum = statEnum;
    }
}

