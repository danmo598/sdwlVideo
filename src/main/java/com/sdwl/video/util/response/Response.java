package com.sdwl.video.util.response;

import java.util.Date;

/**
 */

public class Response {


    private Integer code;


    private String error;


    private Date timestamp;


    private String path;


    private Object extra;


    private Object other;


    public Response() {
    }


    public Response(Integer code, String error, Date timestamp, String path) {
        this.code = code;
        this.error = error;
        this.timestamp = timestamp;
        this.path = path;
    }


    public Response(Builder builder){
        this.code = builder.code;
        this.error = builder.error;
        this.timestamp = builder.timestamp;
        this.path = builder.path;
        this.extra = builder.extra;
        this.other = builder.other;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Response{");
        sb.append("code=").append(code);
        sb.append(", error='").append(error).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", path='").append(path).append('\'');
        sb.append(", extra=").append(extra);
        sb.append(", other=").append(other);
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {

        private Integer code;
        private String error;
        private Date timestamp;
        private String path;
        private Object extra;
        private Object other;


        public Builder() {
        }

        public Builder(Integer code, String error, Date timestamp, String path) {
            this.code = code;
            this.error = error;
            this.timestamp = timestamp;
            this.path = path;
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder extra(Object extra) {
            this.extra = extra;
            return this;
        }

        public Builder other(Object other) {
            this.other = other;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
