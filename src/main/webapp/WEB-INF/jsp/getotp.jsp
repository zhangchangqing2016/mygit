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
                    url:"http://localhost:8090/user/getotp",
                    data:{
                        "telphone":telphone
                    },
                    success:function(data)
                    {
                        if(data.status=="success")
                        {
                            //alter("otp已经发送到你的手机上");
                        }else{
                            //alter("otp 发送失败，原因为:"+data.data.errMsg);
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

<body>
<div>
    <h3>获取otp信息</h3>
    <div>
        <label>手机号</label>
        <div>
            <input type="text" name="telphone" id="telphone" placeholder="手机号"/>
        </div>
    </div>
    <div>
        <button id="getotp" type="submit">
            获取otp短信
        </button>
    </div>
</div>
</body>

