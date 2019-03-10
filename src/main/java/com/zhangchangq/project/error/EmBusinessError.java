package com.zhangchangq.project.error;

public enum EmBusinessError implements CommonError {

    //通用的错误类型00001
    PARAMMETER_VALIDATION_ERROR(00001, "参数不合法"),

    //10000开通为用户错误信息定义
    USER_NOT_EXIST(10001, "用户不存在");


    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrorMessage(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
