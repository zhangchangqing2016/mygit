<%--
  Created by IntelliJ IDEA.
  User: zhangchangq
  Date: 2019/3/24
  Time: 17:45
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

        //定义全局商品信息数组
        var g_itemList = [];

        jQuery(document).ready(function () {


            $.ajax({

                type: "GET",
                url: "/item/list",
                success: function (data) {
                    if (data.status == "success") {
                        g_itemList = data.data;
                        reloadDom();

                    } else {
                        jqueryAlert({
                            'icon': '/static/assets/plugins/alert/img/warning.png',
                            'content': "获取商品信息失败,失败原因" + data.data.errMsg,
                            'closeTime': 2000,
                        });
                    }

                },
                error: function (data) {
                    jqueryAlert({
                        'icon': '/static/assets/plugins/alert/img/error.png',
                        'content': "获取商品信息失败,失败原因" + data.responseText,
                        'closeTime': 2000,
                    });
                }
            });

        });

        function reloadDom() {
            for (var i = 0; i < g_itemList.length; i++) {
                var itemVo = g_itemList[i];
                var dom = "<tr data-id='"+itemVo.id+"' id='itemDetail"+itemVo.id+"'><td>" + itemVo.title + "</td><td><img style='width: 100px;height: auto' src= " + itemVo.imgUrl + "/></td>" +
                    "<td>" + itemVo.description + "</td><td>" + itemVo.price + "</td><td>" + itemVo.stock + "</td>" +
                    "<td>" + itemVo.sales + "</td></tr>";
                $('#container').append($(dom));

                $('#itemDetail'+itemVo.id).on("click",function (e) {
                    window.location.href = "/static/html/getItem.html?id="+$(this).data("id");
                });
            }

        }
    </script>
</head>

<body>
<div class="content">
    <h3 class="form-title">商品列表浏览</h3>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>商品图片</th>
                <th>商品描述</th>
                <th>商品价格</th>
                <th>商品库存</th>
                <th>商品销量</th>
            </tr>
            </thead>
            <tbody id="container">

            </tbody>
        </table>
    </div>
</div>
</body>




