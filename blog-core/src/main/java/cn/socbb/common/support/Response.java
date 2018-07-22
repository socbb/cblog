package cn.socbb.common.support;

import java.util.HashMap;

/**
 * 返回结果
 * Created by cbb on 2018/7/21.
 */
public class Response extends HashMap<String, Object> {

    private static final long serialVersionUID = 2789237333781111133L;

    // 成功
    private static final Integer SUCCESS = 200;
    // 异常 失败
    private static final Integer FAIL = 500;

    public static Response error(String msg) {
        Response response = new Response();
        response.put("code", FAIL);
        response.put("msg", msg);
        return response;
    }

    public static Response success() {
        Response response = new Response();
        response.put("code", SUCCESS);
        return response;
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.put("code", SUCCESS);
        response.put("data", data);
        return response;
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }
}
