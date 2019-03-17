package com.zhangchangq.project.controller;

import com.alibaba.druid.util.StringUtils;
import com.zhangchangq.project.controller.viewobject.UserVo;
import com.zhangchangq.project.error.BusinessException;
import com.zhangchangq.project.error.EmBusinessError;
import com.zhangchangq.project.response.CommonReturnType;
import com.zhangchangq.project.service.UserService;
import com.zhangchangq.project.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/")
    public String initPage() {

        return "getotp";
    }


    @RequestMapping("/registeron")
    public String registerOn() {

        return "register";
    }

    //用户注册接口
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType rigister(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age) throws BusinessException {

        //验证手机号和对应的otpCode是否相符
        String inSessionOtpCode = (String) this.request.getSession().getAttribute(telphone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMMETER_VALIDATION_ERROR, "短信验证码不符合");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    //用户获取otp短信的接口
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    @CrossOrigin
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String phone) {
        //需要按照一定的规则生成otp验证码
        Random random = new Random();
        int randInt = random.nextInt(99999);
        randInt += 100000;
        String otpCode = String.valueOf(randInt);
        //将otp验证码同对应的用户的手机号关联,使用httpsession绑定
        request.getSession().setAttribute(phone, otpCode);


        //将otp验证码通过短信通道发生给用户,
        System.out.println("telphone=" + phone + "&otpCode" + otpCode);


        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVo userVo = convertFromModel(userModel);
        //返回通用对象
        return CommonReturnType.create(userVo);
    }

    private UserVo convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel, userVo);
        return userVo;
    }


}
