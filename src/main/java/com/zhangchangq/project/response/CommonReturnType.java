package com.zhangchangq.project.response;

public class CommonReturnType {
    //对应请求的处理结果“success”||"fail"
    private String status;
    //若status=success,则返回前段需要的json数据
    //若status=fail，则使用通用的错误码格式
    private Object data;

    //通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(result);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonReturnType{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
