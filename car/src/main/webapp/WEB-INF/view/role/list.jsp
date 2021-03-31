<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dtree/dtree.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dtree/font/dtreefont.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">角色名</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="角色名" id="name">
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
    </script>
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-sm" lay-event="edit">
            <i class="layui-icon layui-icon-edit"></i>
            修改
        </button>
        <button class="layui-btn layui-btn-sm" lay-event="setRolePerm">
            <i class="layui-icon layui-icon-set"></i>
            设置权限
        </button>
    </script>
</form>

<%-- 新增角色模板 --%>
<script type="text/html" id="editTpl">
    <form class="layui-form layui-form-pane" style="margin: 10px" lay-filter="editFormFilter">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="required" lay-reqText="请输入角色名" placeholder="角色名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色标识</label>
            <div class="layui-input-inline">
                <input type="text" name="tag" lay-verify="required" lay-reqText="请输入角色标识" placeholder="角色标识" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="descp" placeholder="角色描述"></textarea>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>

<%-- 设置角色权限模板 --%>
<script type="text/html" id="setRolePermTpl">
    <ul id="allPermission" class="dtree" data-id="0"></ul>
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    var cxt = '${pageContext.request.contextPath}';
    layui.extend({
        dtree: cxt + '/resources/dtree/dtree'
    }).use(['dtree', 'layer', 'jquery', 'table', 'form'], function () {
        let dtree = layui.dtree;
        let layer = layui.layer;
        let $ = layui.jquery;
        let table = layui.table;
        let form = layui.form;

        //渲染数据表格
        //表格参数
        let tabOps = {
            id: "dataTableId",
            elem: "#dataTable",
            url: cxt + "/role/page.do",
            page: true, // 开启分页
            toolbar: "#headBtns",//头工具栏
            cols: [[
                {field: "name", align: "center", title: "角色名"},
                {field: "tag", align: "center", title: "角色标识"},
                {field: "descp", align: "center", title: "描述", minWidth: 300},
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
            let name = $("#name").val();
            tabIns.reload({
                where: {
                    "name": name,
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

        function add() {
            //弹出层的参数
            let layOps = {
                title: "编辑角色",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#editTpl").html(),
                area: ['340px', '450px'],
                success: function (layero, index) {
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        //使用ajax提交数据
                        $.post(cxt + "/role/add.do", formData, function (rs) {
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
            if (event == "edit") {
                roleEdit(rowData);
            } else if (event == "setRolePerm") {
                setRolePerm(rowData);
            }
        })

        /**
         * 修改角色信息
         * @param rowData
         */
        function roleEdit(rowData) {
            //弹出层的参数
            let layOps = {
                title: "编辑角色",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#editTpl").html(),
                area: ['340px', '450px'],
                success: function (layero, index) {
                    //表单赋值
                    form.val("editFormFilter", rowData);
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        formData.id = rowData.id;//角色ID 赋值
                        //使用ajax提交数据
                        $.post(cxt + "/role/update.do", formData, function (rs) {
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

        /**
         * 设置角色权限
         * @param rowData
         */
        function setRolePerm(rowData) {
            //弹出层的参数
            let layOps = {
                title: "设置权限",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#setRolePermTpl").html(),
                area: ['340px', '540px'],
                offset: 't',
                success: function (layero, index) {
                    //渲染所有权限的树
                    let dtreeOpt = {
                        elem: "#allPermission",//树容器ID
                        url: cxt + "/permission/all.do",//数据接口   JSON数据地址
                        dataFormat: "list",  //配置data的风格为list
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        response: {message: "msg", statusCode: 200},  //修改response中返回数据的定义
                        checkbar: true,//开启复选框   还依赖数据中 还有 "checkArr": "0", //  0 未选中  1 选中
                        done: function (res, $ul, first) {
                            //设置已有权限为选中状态
                            // dtree.chooseDataInit("allPermission", "1,2,3");

                            //获取角色所有的权限ID
                            $.get(cxt + "/role/permissionIds.do", {id: rowData.id}, function (rs) {
                                if (rs.code == 200) {
                                    //角色对应的权限ID
                                    let pId = rs.data;// 数组
                                    let checkedIds = pId.join(",");
                                    allPermTree.chooseDataInit(checkedIds);
                                }
                            })
                        }
                    };
                    //根据参数渲染
                    let allPermTree = dtree.render(dtreeOpt);
                },
                btn: ['确认', '取消'],
                btnAlign: "c",
                yes: function (index, layero) {
                    //获取角色ID
                    let roleId = rowData.id;
                    //获取所有选中的权限ID
                    let params = dtree.getCheckbarNodesParam("allPermission");
                    let permIds = new Array();
                    for (let permission of params) {
                        let pId = permission.nodeId;
                        permIds.push("permissionId=" + pId);
                    }
                    let permissionId = permIds.join("&");
                    //将角色ID 和 权限ID提交
                    $.post(cxt + "/role/setRolePermission.do?roleId=" + roleId + "&" + permissionId, function (rs) {
                        layer.msg(rs.msg);
                        layer.close(index);
                    });
                    return false;
                }
            };
            layer.open(layOps);
        }

    })
</script>
</body>
</html>