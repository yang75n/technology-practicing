package com.yqw.java_json_xml.interchange.util;

import com.alibaba.fastjson.JSON;

/**
 * @Date 2019/09/09
 */
public class JsonUtils {

    /**
     * 将java对象转换为json字符串
     */
    public static <T> String object2Json(T t) {
        return JSON.toJSONString(t);
    }

    /**
     * 将json字符串转换为java对象
     */
    public static <T> T json2Object(String jsonStr, Class<T> clazz) {
        return JSON.toJavaObject(JSON.parseObject(jsonStr), clazz);
    }
}
