package com.tasty.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 *@author:zhouxin
 *@date:2021/12/14 10:42
 *@description:添加md5加密工具，给password加密
 */
public class Md5Utils {

    public static String code(String str){

        //选用md5的加密方式
        try{

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());

            //取出str的字节流,由StringBuffer写入为16进制
            byte[] digest = md.digest();
            int tmp;
            StringBuffer buf = new StringBuffer();
            for(int offset = 0; offset<digest.length; offset++){
                tmp = digest[offset];
                if(tmp<0) tmp+=256;
                if(tmp<16) buf.append("0");
                buf.append(Integer.toHexString(tmp));
            }

            return buf.toString();
        }

        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println(code("111111"));
    }
}

/*
通过该工具类，可以获取密码，在main函数中输入自己密码对应的明码，然后运行，可以在控制台获取对应的密码，这个密码是要存储在数据库中的password字段
eg：这里是"111111"字符串，运行main后，获得密码为："96e79218965eb72c92a549dd5a330112"，则将该字符串存储进数据库中
*/
