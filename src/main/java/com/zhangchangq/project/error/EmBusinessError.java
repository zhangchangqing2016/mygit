package com.zhangchangq.project.error;

public enum EmBusinessError implements CommonError {

    //通用的错误类型00001
    PARAMMETER_VALIDATION_ERROR(100001, "参数不合法"),

    UNKONE_ERROR(10002,"未知错误"),
    //10000开通为用户错误信息定义
    USER_NOT_EXIST(20001, "用户不存在"),

    USER_LOGIN_FAIL(2002,"用户手机号或密码不正确");

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
