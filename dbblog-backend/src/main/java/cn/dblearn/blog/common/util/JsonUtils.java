package cn.dblearn.blog.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtils
 *
 * @author bobbi
 * @date 2018/10/08 19:13
 * @email 571002217@qq.com
 * @description Json工具类，依赖 jackson
 */
public class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Json字符串转化成对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T toObj(String jsonString, Class<T> clazz) throws Exception {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * javaBean 列表数组转化成json字符串
     * @param obj
     * @return
     * @throws Exception
     */
    public static String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }


}
