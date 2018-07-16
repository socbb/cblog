package cn.socbb.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

/**
 * 缓存key相关的工具类
 */
public class CacheKeyUtil {

    public static String getMethodParamsKey(Object... obj) {
        if (StringUtils.isEmpty(obj)) {
            return "";
        }
        return "(" + JSON.toJSONString(obj) + ")";
    }

}
