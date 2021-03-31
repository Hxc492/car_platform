package com.toddding.utils;

import com.toddding.common.Constant;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Description:
 * @Author: hxc
 * @Date: 2021/3/9 18:56
 */
public class Md5HashUtil {
    public static String encrypt(String password){
        return new Md5Hash(password, Constant.MD5_SALT,2).toString();
    }
}
