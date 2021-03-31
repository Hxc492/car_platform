<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="姓名" id="realname">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="手机号" id="phone">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">地址</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="地址" id="address">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" id="searchBtn" type="button">搜索</button>
                    <button class="layui-btn layui-btn-primary" type="reset">重置</button>
                </div>
            </div>
        </form>
    </blockquote>

    <table id="dataTable" lay-filter="dataTableFilter"></table>

    <!--操作-->
    <script type="text/html" id="headBtns">
        <button class="layui-btn layui-btn-sm" lay-event="add">
            <i class="layui-icon layui-icon-add-1"></i>
            新增
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="del">
            <i class="layui-icon layui-icon-delete"></i>
            删除
        </button>
    </script>
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-sm" lay-event="resetPwd">
            <i class="layui-icon layui-icon-refresh"></i>
            重置密码
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="setRoles">
            <i class="layui-icon layui-icon-set"></i>
            设置角色
        </button>
    </script>
</form>

<%-- 新增用户模板 --%>
<script type="text/html" id="addUserTpl">
    <form class="layui-form layui-form-pane" style="margin: 10px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="realname" lay-verify="required" lay-reqText="请输入姓名" placeholder="用户姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">登录名</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginName" lay-verify="required" lay-reqText="请输入登录名" placeholder="登录名" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" lay-reqText="请输入手机号" placeholder="手机号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证</label>
                <div class="layui-input-inline">
                    <input type="text" name="idCard" lay-verify="required|idCard" lay-reqText="请输入身份证" placeholder="身份证" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="2" title="女">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="address" placeholder="地址" lay-reqText="请输入地址" lay-verify="required"></textarea>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>

<%-- 设置角色的模板 --%>
<script type="text/html" id="setRoleTpl">
    <form class="layui-form layui-form-pane" style="padding-left: 40px" id="allRoles">
        <%--
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 250px">
                <input type="checkbox" title="管理员" lay-skin="primary"/>
                <input type="checkbox" title="业务员" lay-skin="primary"/>
            </div>
        </div>
        --%>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form'], function () {
        let cxt = '${pageContext.request.contextPath}';
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        //渲染数据表格
        //表格参数
        let tabOps = {
            id: "dataTableId",
            elem: "#dataTable",
            url: cxt + "/sysuser/page.do",
            page: true,//开启分页
            toolbar: "#headBtns",//头工具栏
            cols: [[
                {type: "checkbox"},
                {field: "realname", align: "center", title: "姓名"},
                {field: "loginName", align: "center", title: "登录名"},
                {field: "phone", align: "center", title: "电话"},
                {field: "idCard", align: "center", title: "身份证", minWidth: 160},
                {
                    field: "sex", align: "center", title: "性别", width: 80, templet: function (d) {
                        let sex = d.sex;
                        if (sex == 1) {
                            return "<p style='color: #1385e8'>男</p>";
                        } else if (sex == 2) {
                            return "<p style='color: #e8139a'>女</p>";
                        } else {
                            return "<p style='color: #e51111'>不详</p>";
                        }
                        return "<button class='layui-btn layui-btn-xs'>查看</button>"
                    }
                },
                {field: "address", align: "center", title: "地址"},
                {
                    title: "头像", align: "center", width: 80, templet: function (d) {
                        let img = d.img;
                        return "<button class='layui-btn layui-btn-xs' onclick=showUserImg(\'" + img + "\')>查看</button>"
                    }
                },
                {field: "createTime", align: "center", title: "创建时间", width: 120},
                {title: "操作", align: "center", toolbar: "#rowBtns", minWidth: 230, fixed: "right"}
            ]],//列数据
            parseData: function (rs) {
                return {
                    code: rs.code,
                    msg: rs.msg,
                    count: rs.data.total,
                    data: rs.data.list
                }
            },
            response: {
                statusCode: 200
            }
        };
        //进行渲染
        let tabIns = table.render(tabOps);
        //按钮查询
        $("#searchBtn").click(function () {
            let realname = $("#realname").val();
            let phone = $("#phone").val();
            let address = $("#address").val();
            tabIns.reload({
                where: {
                    "realname": realname,
                    "phone": phone,
                    "address": address
                }
            })
        });

        //表格头工具栏监听事件
        table.on("toolbar(dataTableFilter)", function (d) {
            let event = d.event;
            if (event == "add") {
                add();
            }
        })

        /**
         * 新增用户的方法
         */
        function add() {
            //弹出层的参数
            let layOps = {
                title: "新增用户",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#addUserTpl").html(),
                area: ['680px', '450px'],
                success: function (layero, index) {
                    form.render("radio");
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        //使用ajax提交数据
                        $.post(cxt + "/sysuser/add.do", formData, function (rs) {
                            layer.msg(rs.msg);//展示业务消息
                            if (rs.code != 200) {
                                return false;
                            }
                            layer.close(index);//关闭弹层
                            //刷新表格
                            $("#searchBtn").click();
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
        }

        //表格行工具栏事件
        table.on("tool(dataTableFilter)", function (d) {
            let event = d.event;
            let rowData = d.data;
            if (event == "resetPwd") {
                resetPwd(rowData);
            } else if (event == "setRoles") {
                setRoles(rowData);
            }
        })

        /**
         * 重置密码的方法
         * @param rowData
         */
        function resetPwd(rowData) {
            layer.confirm("确定要重置密码吗?", function (index) {
                $.get(cxt + "/sysuser/reset.do", {id: rowData.id}, function (rs) {
                    //展示业务消息
                    layer.msg(rs.msg);
                    layer.close(index);//关闭弹出层
                    $("#searchBtn").click();//重载表格
                })
            })
        }

        /**
         *  显示头像的方法
         * @param url  图像的网络路径
         */
        window.showUserImg = function (url) {
            let imgData = {
                "title": "用户头像", //相册标题
                "start": 0, //初始显示的图片序号，默认0
                "data": [   //相册包含的图片，数组格式
                    {
                        "alt": "用户头像",
                        "src": cxt + "/" + url, //原图地址
                        "thumb": url //缩略图地址
                    }
                ]
            }
            layer.photos({
                photos: imgData
            });
        }

        /**
         * 设置用户的角色
         * @param rowData
         */
        function setRoles(rowData) {
            //1. 弹层模板
            //2. 显示所有的角色
            //   查询所有的角色
            //3. 默认勾选当前用户拥有的角色
            //   查询当前 用户拥有的角色
            //4. 提交用户 ID 和已经勾选了的角色 ID

            // 弹出层的参数
            let layOps = {
                title: "用户角色管理",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#setRoleTpl").html(),
                area: ['300px', '450px'],
                success: function (layero, index) {
                    /*
                    * <div class="layui-form-item">
                          <div class="layui-input-inline" style="width: 250px">
                              <input type="checkbox" title="管理员" lay-skin="primary" />
                              <input type="checkbox" title="管理员" lay-skin="primary"/>
                          </div>
                      </div>
                    * */

                    //1.获取所有的角色
                    $.get(cxt + "/role/all.do", function (rs) {
                        let roles = rs.data;//所有的角色
                        // 角色容器
                        let rolesContent = $("#allRoles");
                        let header = '<div class="layui-form-item"><div class="layui-input-inline" style="width: 250px">'
                        let footer = '</div></div>';
                        let content = "";
                        //循环拼接 所有的角色
                        for (let i = 0; i < roles.length; i++) {//2条  3条
                            let role = roles[i];
                            let id = role.id;
                            let name = role.name;
                            //第一次循环 拼接头
                            if (i % 2 == 0) {
                                content = content + header;
                            }
                            let body = '<input id="checkbox' + id + '" type="checkbox" name="roleId"  value="' + id + '" title="' + name + '" lay-skin="primary" />'
                            content = content + body;
                            if (i % 2 == 1) {
                                content = content + footer;
                            }
                        }
                        // 奇数情况没有拼接尾，手动拼接一个尾
                        if (roles.length % 2 != 0) {
                            content = content + footer;
                        }
                        rolesContent.append(content);
                        //获取所有用户的角色
                        $.get(cxt + "/role/userRoles.do", {userId: rowData.id}, function (rs) {
                            //获取所有的用户角色
                            let userRoles = rs.data;
                            //如果为true 说明有角色  有角色就要选中角色
                            //js 自动转型  userRole 空的  就是 false
                            //不是空的就是 true
                            if (userRoles) {
                                for (role of userRoles) {
                                    let id = role.id;
                                    //input的Id 属性值
                                    let checkboxId = "checkbox" + id;
                                    //选中这个id 对应的checkbox 设置为checked
                                    //属性选择器
                                    $("#" + checkboxId).prop("checked", true);
                                }
                            }
                            form.render("checkbox");
                        })

                    })
                    form.render("checkbox");
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        //获取选中的复选框
                        //allRoles  ID
                        let checkedBox = $("#allRoles :checked");//
                        //获取选中的复选框的value属性值
                        //存储所有选中的角色ID
                        let roleIds = new Array();
                        $.each(checkedBox, function (index, obj) {
                            let roleId = $(obj).val();
                            //将ID 放入数组
                            roleIds.push("roleId=" + roleId);
                        })
                        console.log(roleIds);
                        let userId = rowData.id;//用户ID
                        //roleId=1&roleId=2&roleId=3
                        let userRoleIds = roleIds.join("&")
                        console.log(userRoleIds);
                        //使用ajax提交数据
                        $.post(cxt + "/role/setRole.do?userId=" + userId + "&" + userRoleIds, function (rs) {
                            layer.msg(rs.msg);//展示业务消息
                            if (rs.code != 200) {
                                return false;
                            }
                            layer.close(index);//关闭弹层
                            //刷新表格
                            $("#searchBtn").click();
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

        }


    })
</script>
</body>
</html>