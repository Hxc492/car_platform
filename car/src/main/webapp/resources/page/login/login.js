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
    form.on("submit(login)",function(data){
        // if(slider.isOk()){//用于表单验证是否已经滑动成功
        //     layer.msg(JSON.stringify(data.field));
        // }else{
        //     layer.msg("请先通过滑块验证");
        // }
        let param = data.field;
        $.post("${pageContext.request.contextPath}/sysuser/login.do", param, function (rs) {
            if (rs.code != 200) {
                layer.msg(rs.msg);
                return false;
            }
            //跳转到管理界面主页
            location.href = "page/main.do";
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
