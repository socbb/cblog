package cn.socbb.common.utils;

import cn.socbb.common.constant.CommonConst;

public class PasswordUtils {

    /**
     * AES 加密
     * @param password 未加密的密码
     * @param salt 盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AesUtils.encrypt(MD5Utils.MD5(salt + CommonConst.AES_SECURITY_KEY), password);
    }

    /**
     * AES 解密
     * @param encryptPassword 加密后的密码
     * @param salt 盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AesUtils.decrypt(MD5Utils.MD5(salt + CommonConst.AES_SECURITY_KEY), encryptPassword);
    }

    public static void main(String[] args) throws Exception {
        String socbb = encrypt("socbb.@", "root");
        System.out.println(socbb);
    }
}
