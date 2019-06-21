package com.ant.shop.admin.utils;


import utils.JsonUtil;

import java.util.Map;
import java.util.TreeMap;

public class MessageUtil {

    public static String getSignStr(String body) {
        Map<String, String> map = JsonUtil.toMap(body);
        TreeMap<String, Object> result = new TreeMap<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }

        if (result.size() == 0) {
            result.put("emptybody", "emptybodysignvalue");
        }

        StringBuilder sb = new StringBuilder();
        for (String key : result.keySet()) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        return sb.toString();
    }
}
