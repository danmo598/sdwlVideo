package com.sdwl.video.util.enums;

/**
 * Created by admin on 2016/7/14.
 */
public enum StatEnum implements IEnum {


    STAT_SUCCESS(10000, "正常返回"),
    STAT_NO_DATA(10001, "无数据返回"),
    USER_NOT_EXIST(10002,"用户不存在"),
    PASSWORD_ERROR(10003,"密码错误"),
    VIDEO_OVER(10004,"视频过大"),
    VIDEO_EMPTY(10005,"视频为空"),
    VIDEO_TYPE_ERROR(10006,"视频格式不对"),
    SERVER_ERROR(10007,"服务器错误");
    // 枚举值
    private final int key;

    // 枚举描述
    private final String desc;


    StatEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }


    @Override
    public int key() {
        return key;
    }

    @Override
    public String desc() {
        return desc;
    }
}
