<%--
  Created by IntelliJ IDEA.
  User: zhangchangq
  Date: 2019/3/14
  Time: 21:50
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


    <script type="text/javascript">

        jQuery(document).ready(function(){
            //绑定otp的click事件，用于获取手机验证码
            $("#getotp").on("click",function(){

                var telphone = $("#telphone").val();
                if (telphone == null && telphone =="") {
                    alter("手机号不能为空");
                    return false;
                }
                $.ajax({

                    type:"POST",
                    contentType:"application/x-www-form-urlencoded",
                    url:"/user/getotp",
                    data:{
                        "telphone":telphone
                    },
                    success:function(data)
                    {
                        if(data.status=="success")
                        {
                            //alter("otp已经发送到你的手机上");
                            window.location.href = "registeron";
                        }else{
                           // alter("otp 发送失败，原因为:"+data.data.errMsg);
                        }

                    },
                    error:function(data)
                    {
                        //alter("otp 发送失败,原因为:"+data.responseText);
                    }
                });
                return false;
            });
        });
    </script>
</head>

<body class="login">
<div class="content">
    <h3 class="form-title">验证</h3>
    <div class="form-group">
        <label class="control-label">手机号</label>
        <div>
            <input type="text" class="form-control" name="telphone" id="telphone" placeholder="手机号"/>
        </div>

    </div>
    <div class="form-actions">
        <button id="getotp" class="btn blue" type="submit">
            获取验证码
        </button>

    </div>
</div>
</body>

