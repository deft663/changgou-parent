package com.changgou.entity;

import lombok.Data;

/**
 * 返回结果实体类
 */
@Data
public class Result<T> {
    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息

    private T data;//返回数据

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T)data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
    public Result(Object data) {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "执行成功";
        this.data= (T) data;
    }
    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "执行成功";
    }

    //getter and setter..
}



