package com.zhangchangq.project.controller;

import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.error.EmBusinessError;
import com.zhangchangq.project.response.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {


    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    //定义exceptionHandler解决违背controller层吸收的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception exception) {
        Map<String, Object> responseData = new HashMap<>();
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            responseData.put("errCode", businessException.getErrorCode());
            responseData.put("errMsg", businessException.getErrorMessage());
            logger.error("业务异常code" + businessException.getErrorCode() + "异常内容msg为:" + businessException.getErrorMessage());
        } else {
            responseData.put("errCode", EmBusinessError.UNKONE_ERROR.getErrorCode());
            responseData.put("errMsg", EmBusinessError.UNKONE_ERROR.getErrorMessage());
            logger.error("业务异常code" + EmBusinessError.UNKONE_ERROR.getErrorCode() + "异常内容msg为" + EmBusinessError.UNKONE_ERROR.getErrorMessage());
        }

        return CommonReturnType.create(responseData, "fail");
    }
}
