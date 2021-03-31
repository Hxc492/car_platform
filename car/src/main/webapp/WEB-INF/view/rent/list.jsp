<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>出租管理</title>
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
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline" style="width: 120px">
                        <input type="text" class="layui-input" placeholder="车牌号" id="num">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">客户名称</label>
                    <div class="layui-input-inline" style="width: 120px">
                        <input type="text" class="layui-input" placeholder="客户名称" id="name">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">出租状态</label>
                    <div class="layui-input-inline" style="width: 120px">
                        <select id="flag">
                            <option value="">状态</option>
                            <option value="1">未还车</option>
                            <option value="2">已还车</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">出租时间</label>
                    <div class="layui-input-inline">
                        <input readonly class="layui-input" id="beginTime" placeholder="出租时间"/>
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
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-sm" lay-event="returnCar">
            <i class="layui-icon layui-icon-ok"></i>
            还车
        </button>
    </script>
</form>
<%-- 新增还车记录模板 --%>
<script type="text/html" id="returnTpl">
    <form class="layui-form layui-form-pane" style="margin: 10px" lay-filter="returnFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">车牌号</label>
                <div class="layui-input-inline">
                    <input type="text" name="num" readonly class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">还车时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="returnTime" id="returnTime" lay-verify="required" lay-reqText="请输入还车时间" placeholder="还车时间" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">赔付金额</label>
                <div class="layui-input-inline">
                    <input type="text" name="payMoney" lay-verify="required" value="0" lay-reqText="请输入赔付金额" placeholder="赔付金额" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">问题</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="problem" placeholder="问题"></textarea>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form', 'laydate'], function () {
        let cxt = '${pageContext.request.contextPath}';
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let laydate = layui.laydate;
        //渲染时间控件
        laydate.render({
            elem: "#beginTime",
            range: "~"
        })
        //渲染数据表格
        //表格参数
        let tabOps = {
            id: "dataTableId",
            elem: "#dataTable",
            url: cxt + "/rent/page.do",
            page: true,//开启分页
            toolbar: "#headBtns",//头工具栏
            cols: [[
                {field: "num", align: "center", title: "车牌号"},
                {
                    field: "type", align: "center", title: "车型", templet: function (d) {
                        let type = d.type;
                        if (type == 1) {
                            return "<p style='color: #00ffb7'>轿车</p>";
                        } else if (type == 2) {
                            return "<p style='color: #00e1ff'>SUV</p>";
                        } else if (type == 3) {
                            return "<p style='color: #ff8400'>超跑</p>";
                        }
                    }
                },
                {field: "rentPrice", align: "center", title: "租金"},
                {field: "deposit", align: "center", title: "押金"},
                {field: "name", align: "center", title: "客户"},
                {field: "beginTime", align: "center", title: "开始时间"},
                {field: "endTime", align: "center", title: "结束时间"},
                {
                    field: "flag", align: "center", title: "状态", width: 80, templet: function (d) {
                        let flag = d.flag;
                        if (flag == 1) {
                            return "<p style='color: #ff5900'>未归还</p>";
                        } else if (flag == 2) {
                            return "<p style='color: #00ffd9'>已归还</p>";
                        }
                    }
                },
                {field: "createTime", align: "center", title: "创建时间", width: 150},
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
            let num = $("#num").val();
            let name = $("#name").val();
            let flag = $("#flag").val();
            let beginTime = $("#beginTime").val();
            tabIns.reload({
                where: {
                    "num": num,
                    "name": name,
                    "flag": flag,
                    "beginTime": beginTime
                }
            })
        });

        //表格行工具栏事件
        table.on("tool(dataTableFilter)", function (d) {
            let event = d.event;
            let rowData = d.data;
            if (event == "returnCar") {
                returnCar(rowData);
            }
        })

        /**
         * 还车方法
         * @param rowData
         */
        function returnCar(rowData) {
            //弹出层的参数
            let layOps = {
                title: "还车",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#returnTpl").html(),
                area: ['680px', '450px'],
                success: function (layero, index) {
                    form.val("returnFormFilter", rowData);
                    //渲染还车时间
                    laydate.render({
                        elem: "#returnTime"
                    })
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        formData.rentId = rowData.id;//出租记录ID
                        //使用ajax提交数据
                        $.post(cxt + "/return/add.do", formData, function (rs) {
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