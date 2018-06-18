package cn.molasoftware.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by SHAOFENG on 2017年8月15日 0015.
 * <p>
 * bean转json格式或者json转bean格式,进行json---java互相转化
 * ver:1.0
 */
@Component
public final class JacksonUtil {

    private static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        //去掉默认的时间戳格式  
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区  
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //空值不序列化  
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时，属性不存在的兼容处理  
        OBJECT_MAPPER.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化时，日期的统一格式  
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理  
        OBJECT_MAPPER.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * bean转json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String bean2Json(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * json2Bean
     *
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        T bean;
        try {
            bean = OBJECT_MAPPER.readValue(jsonStr, objClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    /**
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T json2Collection(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}