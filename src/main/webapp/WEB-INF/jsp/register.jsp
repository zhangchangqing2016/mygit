<%--
  Created by IntelliJ IDEA.
  User: zhangchangq
  Date: 2019/3/17
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8"/>
    <link href="/static/assets/global/plugins/bootstrap/css/bootstrap.min.css"  rel="stylesheet" type="text/css">
    <link href="/static/assets/global/css/components.css" rel="stylesheet"  type="text/css">
    <link href="/static/assets/admin/pages/css/login.css"  rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="/static/assets/plugins/alert/js/alert.js"></script>
    <link href="/static/assets/plugins/alert/css/alert.css"/>


    <script type="text/javascript">



        jQuery(document).ready(function(){





            //绑定otp的click事件，用于获取手机验证码
            $("#register").on("click",function(){

                var telphone = $("#telphone").val();
                var password = $("#password").val();
                var age      = $("#age").val();
                var gender   = $("#gender").val();
                var name     = $("#name").val();
                var otpCode  = $("#otpCode").val();
                if (telphone == null||  telphone =="") {
                    jqueryAlert({
                        'icon'    : '/static/assets/plugins/alert/img/warning.png',
                        'content' : '手机号不能为空!',
                        'closeTime' : 2000,
                    });
                    return false;
                }
                if (password == null || password =="") {
                    jqueryAlert({
                        'icon'    : '/static/assets/plugins/alert/img/warning.png',
                        'content' : '密码不能为空!',
                        'closeTime' : 2000,
                    });
                    return false;
                }
                if (age == null || age =="") {
                    jqueryAlert({
                        'icon'    : '/static/assets/plugins/alert/img/warning.png',
                        'content' : '年龄不能为空!',
                        'closeTime' : 2000,
                    });
                    return false;
                }
                if (gender == null || gender =="") {
                    jqueryAlert({
                        'icon'    : '/static/assets/plugins/alert/img/warning.png',
                        'content' : '性别不能为空!',
                        'closeTime' : 2000,
                    });
                    return false;
                }
                if (name == null || name =="") {
                    jqueryAlert({
                        'icon'    : '/static/assets/plugins/alert/img/warning.png',
                        'content' : '姓名不能为空!',
                        'closeTime' : 2000,
                    });
                    return false;
                }
                if (otpCode == null || otpCode =="") {
                    jqueryAlert({
                        'icon'    : '/static/assets/plugins/alert/img/warning.png',
                        'content' : '验证码不能为空!',
                        'closeTime' : 2000,
                    });
                    return false;
                }
                $.ajax({

                    type:"POST",
                    contentType:"application/x-www-form-urlencoded",
                    url:"/user/register",
                    data:{
                        "telphone":telphone,
                        "password":password,
                        "gender"  :gender,
                        "age"     :age,
                        "name"    :name,
                        "otpCode" :otpCode
                    },
                    success:function(data)
                    {
                        if(data.status=="success")
                        {
                            jqueryAlert({
                                'icon'    : '/static/assets/plugins/alert/img/right.png',
                                'content' : '用户注册成功!',
                                'closeTime' : 2000,
                            });

                        }else{
                            jqueryAlert({
                                'icon'    : '/static/assets/plugins/alert/img/right.png',
                                'content' : "用户注册失败,失败原因"+data.data.errMsg,
                                'closeTime' : 2000,
                            });
                        }

                    },
                    error:function(data)
                    {
                        jqueryAlert({
                            'icon'    : '/static/assets/plugins/alert/img/right.png',
                            'content' : "用户注册失败,失败原因"+data.responseText,
                            'closeTime' : 2000,
                        });
                    }
                });
                return false;
            });
        });
    </script>
</head>

<body class="login">
<div class="content">
    <h3 class="form-title">用户注册</h3>
    <div class="form-group">
        <label class="control-label">手机号</label>
        <div>
            <input type="text" class="form-control" name="telphone" id="telphone" placeholder="手机号"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">验证码</label>
        <div>
            <input type="text" class="form-control" name="otpCode" id="otpCode" placeholder="验证码"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">用户昵称</label>
        <div>
            <input type="text" class="form-control" name="name" id="name" placeholder="用户昵称"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">性别</label>
        <div>
            <input type="text" class="form-control" name="gender" id="gender" placeholder="性别"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">年龄</label>
        <div>
            <input type="text" class="form-control" name="age" id="age" placeholder="年龄"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">密码</label>
        <div>
            <input type="password" class="form-control" name="password" id="password" placeholder="密码"/>
        </div>
    </div>
    <div class="form-actions">
        <button id="register" class="btn yellow" type="submit">
            提交注册
        </button>
    </div>
</div>
</body>


