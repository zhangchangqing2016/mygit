package com.zhangchangq.project.controller;

import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.error.EmBusinessError;
import com.zhangchangq.project.response.CommonReturnType;
import com.zhangchangq.project.service.OrderService;
import com.zhangchangq.project.service.model.OrderModel;
import com.zhangchangq.project.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单控制器
 */
@Controller("order")
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService service;
    @Autowired
    private HttpServletRequest request;

    //封装下单请求
    @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount) throws BusinessException {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        //获取登录用户信息
        UserModel userModel = (UserModel) request.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel = service.createOrder(userModel.getId(), itemId, amount);

        return CommonReturnType.create(null);
    }

}
