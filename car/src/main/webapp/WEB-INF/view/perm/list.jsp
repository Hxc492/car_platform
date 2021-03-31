<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>权限管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dtree/dtree.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dtree/font/dtreefont.css" media="all"/>
    <style>
        html, body {
            height: 100%;
        }
    </style>
</head>
<body>
<div style="width: 16%;float: left;height: 100%;border: #c0c4cc solid 1px">
    <ul id="allPermission" class="dtree" data-id="0"></ul>
</div>
<div style="width: 83%;float: left;height: 100%;">
    <form class="layui-form layui-form-pane" style="margin-top: 10px;padding-left: 10px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">权限名称</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="权限名称" id="title">
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
</div>


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
<%-- 编辑权限模板 --%>
<script type="text/html" id="editPermTpl">
    <form class="layui-form layui-form-pane" style="margin: 10px" lay-filter="permFormFilter">
        <div class="layui-form-item">
            <label class="layui-form-label">父权限</label>
            <div class="layui-input-block">
                <ul id="parentPermission" class="dtree" data-id="0"></ul>
                <input type="hidden" id="parentId" name="parentId" value="0">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">权限名</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" lay-verify="required" lay-reqText="请输入权限名" placeholder="权限名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">图标</label>
                <div class="layui-input-inline">
                    <input type="text" name="icon" placeholder="图标" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">链接</label>
                <div class="layui-input-inline">
                    <input type="text" name="href" placeholder="链接" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">是否展开</label>
                <div class="layui-input-inline">
                    <input type="radio" name="spread" value="0" title="否" checked>
                    <input type="radio" name="spread" value="1" title="是">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-inline">
                    <input type="radio" name="type" value="1" title="菜单" checked>
                    <input type="radio" name="type" value="2" title="按钮">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">标识</label>
                <div class="layui-input-inline">
                    <input type="text" name="tag" lay-verify="required" lay-reqText="请输入权限标识" placeholder="权限标识" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">
                    <input type="text" name="sort" value="0" placeholder="权限排序" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    var cxt = '${pageContext.request.contextPath}';
    layui.extend({
        dtree: cxt + '/resources/dtree/dtree'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
    }).use(['dtree', 'layer', 'jquery', 'table', 'form'], function () {
        let dtree = layui.dtree;
        let layer = layui.layer;
        let $ = layui.jquery;
        let table = layui.table;
        let form = layui.form;
        //渲染左侧所有的菜单
        //渲染dtree 参数
        let dtreeOpt = {
            elem: "#allPermission",//树容器ID
            url: cxt + "/permission/all.do",//数据接口   JSON数据地址
            dataFormat: "list",  //配置data的风格为list
            dataStyle: "layuiStyle",  //使用layui风格的数据格式
            response: {message: "msg", statusCode: 200},  //修改response中返回数据的定义
            //checkbar:true,//开启复选框   还依赖数据中 还有 "checkArr": "0", //  0 未选中  1 选中
        };
        //根据参数渲染
        let allPermTree = dtree.render(dtreeOpt);
        //渲染数据表格
        //表格参数
        let tabOps = {
            id: "dataTableId",
            elem: "#dataTable",
            url: cxt + "/permission/page.do",
            page: true,//开启分页
            toolbar: "#headBtns",//头工具栏
            cols: [[
                {field: "title", align: "center", title: "权限名称", minWidth: 130},
                {
                    field: "icon", align: "center", title: "图标", minWidth: 50, templet: function (d) {
                        let icon = d.icon;
                        return "<i class='layui-icon'>" + icon + "</i>";
                    }
                },
                {field: "href", align: "center", title: "链接", minWidth: 180},
                {
                    field: "spread", align: "center", title: "展开", minWidth: 70, templet: function (d) {
                        let spread = d.spread;
                        if (spread) {
                            return "是";
                        } else {
                            return "否"
                        }
                    }
                },
                {
                    field: "type", align: "center", title: "类型", width: 80, templet: function (d) {
                        let type = d.type;
                        if (type == 1) {
                            return "菜单";
                        } else if (type == 2) {
                            return "按钮";
                        }
                    }
                },
                {field: "tag", align: "center", title: "标识", minWidth: 160},
                {field: "sort", align: "center", title: "排序值", minWidth: 90},
                {field: "parentId", align: "center", title: "父ID", minWidth: 60},
                {title: "操作", align: "center", toolbar: "#rowBtns", minWidth: 220}
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
            tabIns.reload({
                where: {
                    "title": title
                }
            })
        });

        //头工具栏监听事件
        table.on("toolbar(dataTableFilter)", function (d) {
            let event = d.event;
            if (event == "add") {
                add();
            }
        })

        /**
         * 新增权限
         */
        function add() {
            //弹出层的参数
            let layOps = {
                title: "编辑权限",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#editPermTpl").html(),
                area: ['680px', '450px'],
                success: function (layero, index) {
                    //渲染父权限下拉框
                    dtree.renderSelect({
                        elem: "#parentPermission",
                        width: "100%", // 指定树的宽度
                        url: cxt + "/permission/all.do",
                        dataFormat: "list",  //配置data的风格为list
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        response: {message: "msg", statusCode: 200},  //修改response中返回数据的定义
                        //当选择了下拉框中数据 则修改 隐藏框中 parentId
                    });
                    dtree.on("changeSelect('parentPermission')", function (obj) {
                        if (!obj.show) {
                            let param = obj.param;
                            let id = param.parentPermission_select_nodeId;
                            if (id) {//展开后选中元素
                                $("#parentId").val(id);
                            } else {
                                //展开后不选元素
                                $("#parentId").val(0);
                            }
                        }
                        console.log(obj.show); // 下拉树面板是否展开
                        console.log(obj.param); // 点击下拉树选中的参数集
                    });
                    form.render("radio");
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        //使用ajax提交数据
                        $.post(cxt + "/permission/add.do", formData, function (rs) {
                            layer.msg(rs.msg);//展示业务消息
                            if (rs.code != 200) {
                                return false;
                            }
                            layer.close(index);//关闭弹层
                            //刷新表格
                            $("#searchBtn").click();
                            //重新加载所有的权限
                            allPermTree.reload();
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
        //头工具栏监听事件
        table.on("tool(dataTableFilter)", function (d) {
            let event = d.event;
            let rowData = d.data;
            if (event == "edit") {
                update(rowData);
            } else if (event == "delete") {
                permDelete(rowData);
            }
        })

        /**
         * 修改权限信息
         * @param rowData
         */
        function update(rowData) {
            //弹出层的参数
            let layOps = {
                title: "编辑权限",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#editPermTpl").html(),
                area: ['680px', '450px'],
                success: function (layero, index) {
                    form.val("permFormFilter", rowData);
                    //渲染父权限下拉框
                    dtree.renderSelect({
                        elem: "#parentPermission",
                        width: "100%", // 指定树的宽度
                        url: cxt + "/permission/all.do",
                        selectInitVal: rowData.parentId + "",
                        dataFormat: "list",  //配置data的风格为list
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        response: {message: "msg", statusCode: 200},  //修改response中返回数据的定义
                        //当选择了下拉框中数据 则修改 隐藏框中 parentId
                    });
                    dtree.on("changeSelect('parentPermission')", function (obj) {
                        if (!obj.show) {
                            let param = obj.param;
                            let id = param.parentPermission_select_nodeId;
                            if (id) {//展开后选中元素
                                $("#parentId").val(id);
                            } else {
                                //展开后不选元素
                                $("#parentId").val(0);
                            }
                        }
                        console.log(obj.show); // 下拉树面板是否展开
                        console.log(obj.param); // 点击下拉树选中的参数集
                    });
                    form.render("radio");
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        formData.id = rowData.id;
                        //使用ajax提交数据
                        $.post(cxt + "/permission/update.do", formData, function (rs) {
                            layer.msg(rs.msg);//展示业务消息
                            if (rs.code != 200) {
                                return false;
                            }
                            layer.close(index);//关闭弹层
                            //刷新表格
                            $("#searchBtn").click();
                            //重新加载所有的权限
                            allPermTree.reload();
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
         * 具体删除权限的方法
         * @param rowData
         */
        function permDelete(rowData) {
            layer.confirm("确定要删除该权限吗?", function (index) {
                $.get(cxt + "/permission/delete.do", {id: rowData.id}, function (rs) {
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

    });
</script>
</body>
</html>
