<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>汽车管理</title>
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
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="车牌号" id="num">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">租金/天</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" placeholder="￥" id="minRentPrice">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" placeholder="￥" id="maxRentPrice">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">车型</label>
                    <div class="layui-input-inline" style="width: 120px">
                        <select id="type">
                            <option value="">车型</option>
                            <option value="1">轿车</option>
                            <option value="2">SUV</option>
                            <option value="3">跑车</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">颜色</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="颜色" id="color">
                    </div>
                </div>

            </div>

            <div class="layui-form-item">

                <div class="layui-inline">
                    <label class="layui-form-label">描述</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="描述" id="descp">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" placeholder="￥" id="minPrice">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" placeholder="￥" id="maxPrice">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">出租状态</label>
                    <div class="layui-input-inline" style="width: 120px">
                        <select id="isRent">
                            <option value="">状态</option>
                            <option value="1">未出租</option>
                            <option value="2">已出租</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline" style="width: 280px" align="center">
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
        <button class="layui-btn layui-btn-sm" lay-event="rent">
            <i class="layui-icon layui-icon-release"></i>
            出租
        </button>
    </script>
</form>
<%-- 新增车辆 --%>
<script type="text/html" id="addTpl">
    <form class="layui-form layui-form-pane" style="padding: 10px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="num" lay-verify="required" lay-reqText="请输入车牌号" placeholder="车牌号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">车型</label>
                    <div class="layui-input-inline">
                        <select name="type" lay-verify="required" lay-reqText="请选择车型">
                            <option value="">车型</option>
                            <option value="1">轿车</option>
                            <option value="2">SUV</option>
                            <option value="3">跑车</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">颜色</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="color" lay-verify="required" lay-reqText="请输入颜色" placeholder="颜色">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">图片上传</label>
                <div class="layui-input-inline">
                    <img id="uploadImg" src="${pageContext.request.contextPath}/resources/images/upload.jpg" style="height: 160px;width:160px;"/>
                    <%-- 隐藏的input 用于接收上传的文件的URL地址 --%>
                    <input id="img" name="img" type="hidden" lay-verify="required" lay-reqText="请上传车辆图片"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">价格</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="price" lay-verify="required|number" lay-reqText="请输入价格" placeholder="价格">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">租金/天</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="rentPrice" lay-verify="required|number" lay-reqText="请输入租金" placeholder="租金">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">押金</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="deposit" lay-verify="required|number" lay-reqText="请输入押金" placeholder="押金">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="descp" class="layui-textarea"></textarea>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>


<%-- 出租车辆 --%>
<script type="text/html" id="rentTpl">
    <form class="layui-form layui-form-pane" style="padding: 10px" lay-filter="rentFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline">
                        <input type="text" readonly class="layui-input" name="num" lay-verify="required" lay-reqText="请输入车牌号" placeholder="车牌号" disabled>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">车型</label>
                    <div class="layui-input-inline">
                        <select name="type" readonly lay-verify="required" lay-reqText="请选择车型" disabled>
                            <option value="">车型</option>
                            <option value="1">轿车</option>
                            <option value="2">SUV</option>
                            <option value="3">跑车</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">颜色</label>
                    <div class="layui-input-inline">
                        <input type="text" readonly class="layui-input" name="color" lay-verify="required" lay-reqText="请输入颜色" placeholder="颜色" disabled>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-inline">
                    <img id="carImg" src="${pageContext.request.contextPath}/resources/images/upload.jpg" style="height: 160px;width:160px;"/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">价格</label>
                <div class="layui-input-inline">
                    <input type="text" readonly class="layui-input" name="price" lay-verify="required|number" lay-reqText="请输入价格" placeholder="价格" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">租金/天</label>
                <div class="layui-input-inline">
                    <input type="text" readonly class="layui-input" name="rentPrice" lay-verify="required|number" lay-reqText="请输入租金" placeholder="租金" disabled>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">押金</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="deposit" lay-verify="required|number" lay-reqText="请输入押金" placeholder="押金" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="idCard" lay-verify="required|idCard" lay-reqText="请输入客户身份证" placeholder="客户身份证">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出租时间</label>
            <div class="layui-input-block" style="width:514px">
                <input type="text" readonly name="rentTime" id="rentTime" class="layui-input"/>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form', 'upload', 'laydate'], function () {
        let cxt = '${pageContext.request.contextPath}';
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let upload = layui.upload;
        let laydate = layui.laydate;
        //渲染数据表格
        //表格参数
        let tabOps = {
            id: "dataTableId",
            elem: "#dataTable",
            url: cxt + "/car/page.do",
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
                {field: "color", align: "center", title: "颜色"},
                {field: "price", align: "center", title: "价格"},
                {field: "rentPrice", align: "center", title: "租金/天"},
                {field: "deposit", align: "center", title: "押金"},
                {
                    field: "isRent", align: "center", title: "状态", width: 80, templet: function (d) {
                        let isRent = d.isRent;
                        if (isRent == 1) {
                            return "<p style='color: #00ffd9'>未出租</p>";
                        } else if (isRent == 2) {
                            return "<p style='color: #ff5900'>已出租</p>";
                        }
                    }
                },
                {field: "descp", align: "center", title: "描述", minWidth: 130},
                {
                    title: "图片", align: "center", width: 80, templet: function (d) {
                        let img = d.img;
                        return "<button class='layui-btn layui-btn-xs' onclick=showUserImg(\'" + img + "\')>查看</button>"
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
            let type = $("#type").val();
            let color = $("#color").val();
            let minPrice = $("#minPrice").val();
            let maxPrice = $("#maxPrice").val();
            let minRentPrice = $("#minRentPrice").val();
            let maxRentPrice = $("#maxRentPrice").val();
            let isRent = $("#isRent").val();
            let descp = $("#descp").val();
            tabIns.reload({
                where: {
                    "num": num,
                    "type": type,
                    "color": color,
                    "minPrice": minPrice,
                    "maxPrice": maxPrice,
                    "minRentPrice": minRentPrice,
                    "maxRentPrice": maxRentPrice,
                    "isRent": isRent,
                    "descp": descp
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
         * 新增车辆的方法
         */
        function add() {
            //弹出层的参数
            let layOps = {
                title: "新增车辆",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#addTpl").html(),
                area: ['710px', '580px'],
                success: function (layero, index) {
                    form.render();
                    /* 初始化上传组件 */
                    //初始化文件上传
                    let uploadOpt = {
                        elem: "#uploadImg",
                        url: cxt + "/file/uploadImage.do",//处理文件上传的接口
                        auto: true,//自动上传
                        field: "image",//指定文件上传的数据域名称
                        choose: function (obj) {
                            //文件预览
                            obj.preview(function (index, file, result) {
                                //修改图片src属性值 实现预览
                                $("#uploadImg").attr("src", result);
                            })
                        },
                        done: function (rs, fileIndex, upload) {
                            //将业务消息展示
                            layer.msg(rs.msg);
                            if (rs.code == 200) {
                                //给隐藏框赋值
                                $("#img").val(rs.data);
                            }
                        }
                    };
                    upload.render(uploadOpt);
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        //使用ajax提交数据
                        $.post(cxt + "/car/add.do", formData, function (rs) {
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
            if (event == "rent") {
                rent(rowData);//具体的出租方法
            }
        })

        //出租方法
        function rent(rowData) {
            //弹出层的参数
            let layOps = {
                title: "车辆出租",
                skin: "layui-layer-molv",
                type: 1,
                content: $("#rentTpl").html(),
                area: ['710px', '580px'],
                success: function (layero, index) {
                    //为表单赋值
                    form.val("rentFormFilter", rowData);
                    //为图片设置数据
                    $("#carImg").attr("src", cxt + "/" + rowData.img);
                    //渲染时间组件
                    laydate.render({
                        elem: "#rentTime",
                        range: "~"
                    })
                    //表单的提交监听
                    form.on("submit(subBtnFilter)", function (d) {
                        let formData = d.field;
                        //使用ajax提交数据
                        $.post(cxt + "/rent/add.do", formData, function (rs) {
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
         *  显示头像的方法
         * @param url  图像的网络路径
         */
        window.showUserImg = function (url) {
            let imgData = {
                "title": "车辆图片", //相册标题
                "start": 0, //初始显示的图片序号，默认0
                "data": [   //相册包含的图片，数组格式
                    {
                        "alt": "车辆图片",
                        "src": cxt + "/" + url, //原图地址
                        "thumb": url //缩略图地址
                    }
                ]
            }
            layer.photos({
                photos: imgData
            });
        }
    })
</script>
</body>
</html>