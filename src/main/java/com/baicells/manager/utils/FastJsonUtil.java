package com.baicells.manager.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.List;
import java.util.Map;


public class FastJsonUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(FastJsonUtil.class);


    private static final SerializeConfig config;
    private static final SimpleDateFormatSerializer formatSerializer = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, formatSerializer);
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 转为JSON 格式字符串 (输出空置字段,输出空list,map,数值)
     *
     * @param object
     * @return
     */

    public static String toJSONString(Object object) {
        try {
            return JSON.toJSONString(object, config, features);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("json转换Str失败！", e);
            return null;
        }

    }

    /**
     * 转为JSON 格式字符串 无配置
     *
     * @param object
     * @return
     */
    public static String toJSONNoFeatures(Object object) {
        try {
            return JSON.toJSONString(object, config, SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("json转换Str失败！", e);
            return null;
        }
    }

    /**
     * @param text
     * @return
     */
    public static Object toBean(String text) {
        try {
            return JSON.parse(text);
        } catch (Exception e) {
            logger.error("json转换Bean失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(String text, Class<T> clazz) {

        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            logger.error("json转换指定Bean失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    // 转换为数组
    public static <T> Object[] toArray(String text) {

        try {
            return toArray(text, null);
        } catch (Exception e) {
            logger.error("json转换数组失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    // 转换为数组
    public static <T> Object[] toArray(String text, Class<T> clazz) {

        try {
            return JSON.parseArray(text, clazz).toArray();
        } catch (Exception e) {
            logger.error("json转换指定类型数组失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {

        try {
            return JSON.parseArray(text, clazz);
        } catch (Exception e) {
            logger.error("json转换指定类型List失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(KeyValue keyvalue) {
        try {
            String textJson = JSON.toJSONString(keyvalue);
            Object objectJson = JSON.parse(textJson);
            return objectJson;
        } catch (Exception e) {
            logger.error("将javabean转化为序列化的json字符串失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param
     * @return
     */
    public static Object textToJson(String text) {
        try {
            Object objectJson = JSON.parse(text);
            return objectJson;
        } catch (Exception e) {
            logger.error("将string转化为序列化的json字符串失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        try {
            Map m = JSONObject.parseObject(s);
            return m;
        } catch (Exception e) {
            logger.error("json字符串转化为map失败！", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        try {
            String s = JSONObject.toJSONString(m);
            return s;
        } catch (Exception e) {
            logger.error("将map转化为string失败！", e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把对象转换为指定对象
     */
    public static <T> T toObjectFromSource(Object source, Class<T> target) {
        return toBean(toJSONString(source), target);
    }


}
