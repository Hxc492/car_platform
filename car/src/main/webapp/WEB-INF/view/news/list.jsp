<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新闻公告</title>
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
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="标题" id="title">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="内容" id="content">
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
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">
            <i class="layui-icon layui-icon-delete"></i>
            删除
        </button>
    </script>
</form>
<%-- 编辑新闻公告的模板 --%>
<script type="text/html" id="editTpl">
    <form class="layui-form layui-form-pane" style="margin: 10px" lay-filter="editFormFilter">
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="title" placeholder="标题" lay-reqText="请输入标题" lay-verify="required"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="content" placeholder="内容" lay-reqText="请输入内容" lay-verify="required"></textarea>
            </div>
        </div>
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
            url: cxt + "/news/page.do",
            page: true,//开启分页
            toolbar: "#headBtns",//头工具栏
            cols: [[
                {type: "checkbox"},
                {field: "title", align: "center", title: "标题",minWidth: 150},
                {field: "content", align: "center", title: "内容",minWidth: 250},
                {field: "createTime", align: "center", title: "创建时间", width: 120},
                {title: "操作", align: "center", toolbar: "#rowBtns", fixed: "right"}
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
            let title = $("#title").val();
            let content = $("#content").val();
            tabIns.reload({
                where: {
                    "title": title,
                    "content": content
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

        //弹出层的参数
        let layOps = {
            title: "编辑新闻公告",
            skin: "layui-layer-molv",
            type: 1,
            content: $("#editTpl").html(),
            area: ['425px', '425px'],
            success: function (layero, index) {
                form.render("radio");
                //表单的提交监听
                form.on("submit(subBtnFilter)", function (d) {
                    let formData = d.field;
                    //使用ajax提交数据
                    $.post(cxt + "/news/add.do", formData, function (rs) {
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

        /**
         * 新增新闻公告的方法
         */
        function add() {
            layer.open(layOps);
        }

        //行工具栏监听事件
        table.on("tool(dataTableFilter)", function (d) {
            let event = d.event;
            let rowData = d.data;
            if (event == "edit") {
                edit(rowData);//修改新闻公告
            }else if (event == "delete"){
                deletedNews(rowData);//删除新闻公告
            }
        });

        //具体的修改方法
        function edit(rowData) {
            //将对象复制一份
            let ops = new Object();//目标对象
            Object.assign(ops, layOps);//将layOps 对象复制给 ops 对象
            ops.success = function (layero, index) {
                form.render("radio");
                form.val("editFormFilter", rowData); // 进行数据回显
                //表单的提交监听
                form.on("submit(subBtnFilter)", function (d) {
                    let formData = d.field;
                    formData.id = rowData.id;//获取ID
                    //使用ajax提交数据
                    $.post(cxt + "/news/update.do", formData, function (rs) {
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
            }
            layer.open(ops);
        }

        /**
         * 具体删除新闻公告的方法
         * @param rowData
         */
        function deletedNews(rowData) {
            layer.confirm("确定要删除该新闻公告吗?", function (index) {
                $.get(cxt + "/news/delete.do", {id: rowData.id}, function (rs) {
                    layer.msg(rs.msg);
                    if (rs.code == 200) {
                        //刷新表格
                        $("#searchBtn").click();
                        //重新加载所有的权限
                        allPermTree.reload();
                    }
                    layer.close(index);
                });
            })
        }

    })
</script>
</body>
</html>