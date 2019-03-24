<%--
  Created by IntelliJ IDEA.
  User: zhangchangq
  Date: 2019/3/24
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8"/>
    <link href="/static/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/static/assets/global/css/components.css" rel="stylesheet" type="text/css">
    <link href="/static/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="/static/assets/plugins/alert/js/alert.js"></script>
    <link href="/static/assets/plugins/alert/css/alert.css"/>


    <script type="text/javascript">


        jQuery(document).ready(function () {

            //绑定otp的click事件，用于获取手机验证码
            $("#create").on("click", function () {

                var title = $("#title").val();
                var description = $("#description").val();
                var imgUrl = $("#imgUrl").val();
                var price = $("#price").val();
                var stock = $("#stock").val();
                if (title == null || title == "") {
                    jqueryAlert({
                        'icon': '/static/assets/plugins/alert/img/warning.png',
                        'content': '商品名不能为空!',
                        'closeTime': 2000,
                    });
                    return false;
                }
                if (description == null || description == "") {
                    jqueryAlert({
                        'icon': '/static/assets/plugins/alert/img/warning.png',
                        'content': '描述不能为空!',
                        'closeTime': 2000,
                    });
                    return false;
                }
                if (imgUrl == null || imgUrl == "") {
                    jqueryAlert({
                        'icon': '/static/assets/plugins/alert/img/warning.png',
                        'content': '图片不能为空!',
                        'closeTime': 2000,
                    });
                    return false;
                }
                if (price == null || price == "") {
                    jqueryAlert({
                        'icon': '/static/assets/plugins/alert/img/warning.png',
                        'content': '价格不能为空!',
                        'closeTime': 2000,
                    });
                    return false;
                }
                if (stock == null || stock == "") {
                    jqueryAlert({
                        'icon': '/static/assets/plugins/alert/img/warning.png',
                        'content': '库存不能为空!',
                        'closeTime': 2000,
                    });
                    return false;
                }
                $.ajax({

                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    url: "/item/create",
                    data: {
                        "title": title,
                        "description": description,
                        "imgUrl": imgUrl,
                        "price": price,
                        "stock": stock

                    },
                    success: function (data) {
                        if (data.status == "success") {
                            jqueryAlert({
                                'icon': '/static/assets/plugins/alert/img/right.png',
                                'content': '创建成功!',
                                'closeTime': 2000,
                            });

                        } else {
                            jqueryAlert({
                                'icon': '/static/assets/plugins/alert/img/warning.png',
                                'content': "创建失败,失败原因" + data.data.errMsg,
                                'closeTime': 2000,
                            });
                        }

                    },
                    error: function (data) {
                        jqueryAlert({
                            'icon': '/static/assets/plugins/alert/img/error.png',
                            'content': "创建失败,失败原因" + data.responseText,
                            'closeTime': 2000,
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
    <h3 class="form-title">创建商品</h3>
    <div class="form-group">
        <label class="control-label">商品名</label>
        <div>
            <input type="text" class="form-control" name="title" id="title" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">商品描述</label>
        <div>
            <input type="text" class="form-control" name="description" id="description"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">价格</label>
        <div>
            <input type="text" class="form-control" name="price" id="price" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">图片</label>
        <div>
            <input type="text" class="form-control" name="imgUrl" id="imgUrl" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">库存</label>
        <div>
            <input type="text" class="form-control" name="stock" id="stock" />
        </div>
    </div>
    <div class="form-actions">
        <button id="create" class="btn yellow" type="submit">
            提交创建
        </button>
    </div>
</div>
</body>



