
package cn.socbb.common.utils;

import cn.socbb.common.constant.SessionConst;
import cn.socbb.common.support.RequestHolder;
import cn.socbb.core.bean.system.User;

import java.util.UUID;

/**
 * Session工具类
 *
 */
public class SessionUtil {

    /**
     * 获取session中的用户信息
     *
     * @return User
     */
    public static User getUser() {
        Object session = RequestHolder.getSession(SessionConst.USER_SESSION_KEY);
        return (User) session;
    }

    /**
     * 添加session
     *
     * @param user
     */
    public static void setUser(User user) {
        RequestHolder.setSession(SessionConst.USER_SESSION_KEY, user);
    }

    /**
     * 删除session信息
     */
    public static void removeUser() {
        RequestHolder.removeSession(SessionConst.USER_SESSION_KEY);
    }

    /**
     * 获取session中的Token信息
     *
     * @return String
     */
    public static String getToken(String key) {
        return (String) RequestHolder.getSession(key);
    }

    /**
     * 添加Token
     */
    public static void setToken(String key) {
        RequestHolder.setSession(key, UUID.randomUUID().toString());
    }

    /**
     * 删除Token信息
     */
    public static void removeToken(String key) {
        RequestHolder.removeSession(key);
    }

    /**
     * 获取验证码
     */
    public static String getKaptcha() {
        return (String) RequestHolder.getSession(SessionConst.KAPTCHA_SESSION_KEY);
    }

    /**
     * 保存验证码
     */
    public static void setKaptcha(String kaptcha) {
        RequestHolder.setSession(SessionConst.KAPTCHA_SESSION_KEY, kaptcha);
    }

    /**
     * 保存验证码
     */
    public static void removeKaptcha() {
        RequestHolder.removeSession(SessionConst.KAPTCHA_SESSION_KEY);
    }

    /**
     * 删除所有的session信息
     */
    public static void removeAllSession() {
        String[] keys = RequestHolder.getSessionKeys();
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                RequestHolder.removeSession(key);
            }
        }
    }
}
