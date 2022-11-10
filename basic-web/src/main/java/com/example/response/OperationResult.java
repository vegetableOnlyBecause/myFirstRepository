package com.example.response;

import java.io.Serializable;

/**
 * @title: 响应信息
 * @author: vegetableOnlyBecause
 * @date 2022/9/21 16:19
 * @description:
 */
public class OperationResult<T> implements Serializable {
    private static final long serialVersionUID = -8307738997040116459L;

    private boolean flag;
    private String errCode;
    private String errMessage;
    private T resData;

    private OperationResult(boolean flag) {
        this.flag = flag;
    }

    private OperationResult(boolean flag, T resData) {
        this(flag);
        this.resData = resData;
    }

    public OperationResult(boolean flag, String errCode, String errMessage) {
        this(flag);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public static OperationResult succ(){
        return new OperationResult(Boolean.TRUE);
    }

    public static <T> OperationResult<T> succ(T resData){
        return new OperationResult(Boolean.TRUE, resData);
    }

    public static  OperationResult fail(String errCode, String errMessage){
        return new OperationResult(Boolean.FALSE, errCode, errMessage);
    }

    public static OperationResult fail(String errCode){
        return fail(errCode, errCode);
    }

    public boolean isFlag() {
        return flag;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public T getResData() {
        return resData;
    }
}
