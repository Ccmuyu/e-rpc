package com.zzw.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class JsonUtil {

    public static String jsons(Object o) {
        return JSON.toJSONString(o);
    }


    public static Map<String, Object> parse(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject;
    }

    public static <T> T parse(String json, Class<T> type) {
        return JSON.parseObject(json, type);
    }

    public static <T> List<T> parseList(String json, Class<T> type) {
        List<T> ts = JSON.parseObject(json, new TypeReference<List<T>>() {
        });
        return ts;
    }
}
