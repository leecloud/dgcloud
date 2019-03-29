package com.lee.cloudcommon.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Json工具类
 */
public class JsonUtils {
    /**
     * bean对象转json
     */
    public static String beanToJson(Object object, String dataFormatString){
        if (object != null){
            if (StringUtils.isEmpty(dataFormatString)){
                return JSONObject.toJSONString(object);
            }
            return JSONObject.toJSONStringWithDateFormat(object,dataFormatString);
        }else {
            return null;
        }
    }

    public static String beanToJson(Object object){
        if (object != null){
            return JSON.toJSONString(object);
        }else {
            return null;
        }
    }
    /**
     * String 转JSOn
     */
    public static String StringToJsonByFastjson(String key, String value){
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)){
            return null;
        }
        Map<String, String> map = new HashMap<>(16);
        map.put(key,value);
        return beanToJson(map,null);
    }
    /**
     * json转换成对象
     */
    public static Object jsonToBean(String json, Object clazz){
        if (StringUtils.isEmpty(json) || clazz == null){
            return null;
        }
        return JSON.parseObject(json,clazz.getClass());
    }
    /**
     * json转map
     */
    public static Map<String, Object> jsonToMap(String json){
        if (StringUtils.isEmpty(json)){
            return null;
        }
        return JSON.parseObject(json,Map.class);
    }
}
