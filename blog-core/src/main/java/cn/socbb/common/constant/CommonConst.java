
package cn.socbb.common.constant;

import java.util.UUID;

/**
 * 程序中公用的常量类
 *

 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class CommonConst {
    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String AES_SECURITY_KEY = "0350141dcd5a471d997ebfcbd6cab3b4";

    /**
     * 程序默认的错误状态码
     */
    public static final int DEFAULT_ERROR_CODE = 500;

    /**
     * 程序默认的成功状态码
     */
    public static final int DEFAULT_SUCCESS_CODE = 200;

}
