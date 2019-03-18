package com.zhangchangq.project.service;

import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.service.model.UserModel;


public interface UserService {

    UserModel getUserById(Integer id) ;

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}
