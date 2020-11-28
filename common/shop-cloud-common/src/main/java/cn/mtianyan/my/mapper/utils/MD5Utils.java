package cn.mtianyan.my.mapper.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @Description: 对字符串进行md5加密
 * Create By mtianyan
 * 2019/12/26 20:31
 */
public class MD5Utils {
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newStr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newStr;
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5Str("mtianyan");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
