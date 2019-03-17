package com.zhangchangq.project.service;

import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.service.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    UserModel getUserById(Integer id) ;

    void register(UserModel userModel) throws BusinessException;
}
