package com.tasty.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 *@author:zhouxin
 *@date:2021/12/14 10:42
 *@description:添加md5加密工具，给password加密
 */
public class Md5Utils {

    public static void code(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e){

        }
    }

}
