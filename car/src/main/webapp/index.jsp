<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>汽车租赁·登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
</head>
<body class="loginBody">
<form class="layui-form" action="">
    <div class="login_face"><img src="${pageContext.request.contextPath}/resources/images/face.jpg" width="100%" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="loginPhone">手机号</label>
        <input type="text" placeholder="请输入手机号" autocomplete="off" id="loginPhone" name="loginPhone" class="layui-input" lay-verify="required" value="admin">
    </div>
    <div class="layui-form-item input-item">
        <label for="loginPassword">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" id="loginPassword" name="loginPassword" class="layui-input" lay-verify="required" value="admin">
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 260px;text-align: center">
                <input class="identity" type="radio" name="identity" value="1" title="用户登录" checked>
                <input class="identity" type="radio" name="identity" value="2" title="管理员登录">
            </div>
        </div>
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <div id="slider"></div>
    </div>
    <div class="layui-form-item">
        <button type="button" class="layui-btn layui-block" lay-filter="loginBtn" lay-submit>登录</button>
    </div>
<%--    <div class="layui-form-item layui-row">--%>
<%--        <a href="javascript:;" class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>--%>
<%--        <a href="javascript:;" class="seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>--%>
<%--        <a href="javascript:;" class="seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>--%>
<%--    </div>--%>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/page/login/login.js"></script>--%>
<script>
    layui.config({
        base: 'resources/sliderVerify/'
    }).use(['form','jquery','layer','sliderVerify'],function(){
        let form = layui.form;
        let $ = layui.jquery;
        let layer = parent.layer === undefined ? layui.layer : top.layer;
        let sliderVerify = layui.sliderVerify;
        let slider = sliderVerify.render({
            elem: '#slider'
        })
        $(".loginBody .seraph").click(function(){
            layer.msg("该功能正在开发中。。。",{
                time:5000
            });
        })

        //登录按钮
        form.on("submit(loginBtn)",function(data){
            let param = data.field;
            console.log(param);
            $.post("${pageContext.request.contextPath}/page/login.do", param, function (rs) {
                if (rs.code == 200){
                    if ($('.identity').value()==1){
                        //跳转到前台首页
                        location.href = "page/home.do";
                    }else {
                        //跳转到后台主页
                        location.href = "page/main.do";
                    }
                } else if (rs.code == 202) {

                }else{
                    layer.msg(rs.msg);
                    return false;
                }
            })
            return false;
        })

        //表单输入效果
        $(".loginBody .input-item").click(function(e){
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function(){
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function(){
            $(this).parent().removeClass("layui-input-focus");
            if($(this).val() != ''){
                $(this).parent().addClass("layui-input-active");
            }else{
                $(this).parent().removeClass("layui-input-active");
            }
        })
    })

</script>
</body>
</html>