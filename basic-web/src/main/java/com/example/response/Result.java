package com.example.response;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/17 9:22
 * @description:
 */
/**
 * Created by IngerJo on 2018/8/31.
 */
public class Result<T> {

    private Integer status;

    private String msg;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

