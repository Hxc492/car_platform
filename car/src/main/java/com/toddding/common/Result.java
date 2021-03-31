package com.toddding.common;

import com.toddding.common.exception.BussiException;
import lombok.Data;

/**
 * @Description: 业务结果包装类
 *   因为layui前端技术 需要 code msg data 这样数据格式
 *   所以后端对数据进行包装
 * @Author: hxc
 * @Date: 2021/3/7 22:32
 */
@Data
public class Result {
    /**
     * 业务码
     */
    private Integer code;
    /**
     * 业务消息
     */
    private String msg;
    /**
     * 业务数据
     */
    private Object data;

    /**
     * 业务就分成2种情况
     *   1.成功
     *      1.1成功了没有数据   删除
     *      1.2成功了有返回数据 查询
     *   2.失败
     *      2.1失败时没有数据   失败原因
     */

    /**
     * 成功没有数据
     */
    public Result(){
        this.code=CodeMsg.SUCCESS.code;
        this.msg=CodeMsg.SUCCESS.msg;
    }

    /**
     * 成功有数据
     * @param data
     */
    public Result(Object data){
        this();
        this.data=data;
    }

    /**
     * 失败 没有数据
     * 但是有 错误码 和 错误消息
     * @param codeMsg
     */
    public Result(CodeMsg codeMsg){
        this.code=codeMsg.code;
        this.msg=codeMsg.msg;
    }

    /**
     * 兼容异常信息
     * @param bussiException
     */
    public Result(BussiException bussiException){
        this.code=bussiException.getCode();
        this.msg=bussiException.getMsg();
    }
}
