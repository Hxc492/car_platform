<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>汽车租赁管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" media="all"/>
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main mag0">
            <a href="#" class="logo">汽车租赁管理</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:;" class="seraph hideMenu icon-caidan"></a>
            <ul class="layui-nav topLevelMenus" pc>
            </ul>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item" pc>
                    <a href="javascript:;" class="clearCache"><i class="layui-icon" data-icon="&#xe640;">&#xe640;</i><cite>清除缓存</cite></a>
                </li>
                <li class="layui-nav-item" id="userInfo">
                    <a href="javascript:;"><img src="${pageContext.request.contextPath}/resources/images/face.jpg" class="layui-nav-img userAvatar" width="35"
                                                height="35"><cite class="adminName"><shiro:principal property="realname"></shiro:principal></cite></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="updatePassword">
                            <i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a></dd>
                        <dd pc><a href="javascript:;" class="changeSkin"><i class="layui-icon">&#xe61b;</i><cite>更换皮肤</cite></a></dd>
                        <dd pc><a href="javascript:;" id="updateImg"><i class="layui-icon">&#xe60d;</i><cite>更换头像(TODO)</cite></a></dd>
                        <dd><a href="javascript:;" id="logout" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的头像"><img src="${pageContext.request.contextPath}/resources/images/face.jpg" class="userAvatar"></a>
            <p>你好！<span class="userName"><shiro:principal property="realname"></shiro:principal></span>, 欢迎登录</p>
        </div>
        <!-- 搜索 -->
        <div class="layui-form component">
            <select name="search" id="search" lay-search lay-filter="searchPage">
                <option value="">搜索页面或功能</option>
                <option value="1">layer</option>
                <option value="2">form</option>
            </select>
            <i class="layui-icon">&#xe615;</i>
        </div>
        <div class="navBar layui-side-scroll" id="navBar">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;" data-url="http://nows.fun"><i class="layui-icon" data-icon=""></i><cite>后台首页</cite></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
                        <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="http://nows.fun"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 修改密码模板 --%>
<script type="text/html" id="updatePasswordTpl">
    <form class="layui-form layui-form-pane" style="margin-top: 15px;margin-left: 15px">
        <div class="layui-form-item">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 当前密码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="password" placeholder="当前密码" lay-verify="required" lay-reqText="当前密码不能为空" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 新 密 码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="newPassword" placeholder="新密码" lay-verify="required" lay-reqText="新密码不能为空" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i> 确认密码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="confirmPassword" placeholder="确认密码" lay-verify="required" lay-reqText="确认密码不能为空" >
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>

<script>
    var cxt = '${pageContext.request.contextPath}'; // /项目路径
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cache.js"></script>
<script>
    layui.use(['jquery', 'form'], function () {
        let $ = layui.jquery;
        let form = layui.form;

        //用户退出事件
        $("#logout").click(function () {
            $.get(cxt + "/page/logout.do", function (rs) {
                if (rs.code == 200) {
                    //退出成功 跳转到登录页面
                    window.location.href = cxt + "/index.jsp";
                }
            })
        });

        /*
        * 修改密码
        * */
        $("#updatePassword").click(function () {
            // 弹出层的参数
            let layOps = {
                title: "修改密码",
                type: 1,
                skin: "layui-layer-molv",
                content: $("#updatePasswordTpl").html(),
                area: ['330px', '350px'],
                success: function (layero, index) {
                    form.render("radio");
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        let newPassword = formData.newPassword;//新密码
                        let confirmPassword = formData.confirmPassword;//确认密码
                        if (newPassword != confirmPassword) {
                            layer.msg("确认密码不一致!")
                            return false;
                        }
                        //使用ajax提交数据
                        $.post(cxt + "/sysuser/updatePassword.do", formData, function (rs) {
                            layer.msg(rs.msg);//展示业务消息
                            if (rs.code != 200) {
                                return false;
                            }
                            layer.close(index);//关闭弹层
                            //重新登录
                            window.location.href = cxt + "/index.jsp";
                        })
                        return false;//阻止默认提交行为
                    })
                },
                btn: ['确认', '取消'],
                btnAlign: "c",
                yes: function (index, layero) {
                    //点击隐藏的提交按钮  触发 表单提交监听
                    $("#subBtn").click();
                }
            };
            layer.open(layOps);
        });

    })
</script>
</body>
</html>