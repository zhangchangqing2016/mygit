package com.zhangchangq.project.service;

import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.service.model.OrderModel;

public interface OrderService {

    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws BusinessException;
}
