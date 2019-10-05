package com.zhou.bot.zhoubottest.utils;


import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class JsonUtils {

	private final static Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper(); //TODO 新版 simleFactory 二进制，高性能

	private JsonUtils() {
	}

	static {
		// 设置输出包含的属性
		OBJECT_MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		OBJECT_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OBJECT_MAPPER.setDateFormat(df);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null.
	 * 如果JSON字符串为"[]",返回空集合.
	 * @param jsonString
	 * @param clazz
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		}
		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, clazz);
		} catch (IOException e) {
			LOG.error("parse json string error:" + jsonString, e);
		}
		return null;
	}

	/**
	 * 读取集合如List/Map,且不是List<String>时.
	 * @param jsonString
	 * @param typeReference
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		}
		try {
			return (T) OBJECT_MAPPER.readValue(jsonString, typeReference);
		} catch (IOException e) {
			LOG.error("parse json string error:" + jsonString, e);
		}
		return null;
	}

	/**
	 * 如果对象为Null,返回"null".
	 * 如果集合为空集合,返回"[]".
	 */
	public static String toJson(Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (IOException e) {
			LOG.warn("write to json string error:" + object, e);
		}
		return null;
	}
	
	/**
	 * 对象转Byte数组
	 * @param obj
	 * @return
	 */
	public static byte[] writeToByteArr(Object obj){
		try {
			return OBJECT_MAPPER.writeValueAsBytes(obj);
		} catch (IOException e) {
			LOG.warn("write to byte error:" + obj.getClass().getName(), e);
		}
		return null;
	}
	
	public static Object readfromByteArr(byte[] byteArr, JavaType javaType){
		if(null==byteArr||byteArr.length==0){
			return null;
		}
		
		try {
			return OBJECT_MAPPER.readValue(byteArr, javaType);
		} catch (Exception e) {
			LOG.warn("byte to Object error:" , e);
		}
		return null;
	}
	
	public static Object readfromByteArr(byte[] byteArr, Class<?> clazz) {
		if (null==byteArr||byteArr.length==0) {
			return null;
		}
		try {
			return OBJECT_MAPPER.readValue(byteArr, clazz);
		} catch (IOException e) {
			LOG.error("byte to Object error:" + clazz.getName(), e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String json) {
		try {
			if (StringUtils.isBlank(json)) {
				return new HashMap<String, Object>();
			}
			return OBJECT_MAPPER.readValue(json, Map.class);
		} catch (Exception e) {
			LOG.error("json2Map(), 出错的json内容: {} exception {}", json, e.getMessage());
		}
		return new HashMap<String, Object>();
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public static ObjectMapper getMapper() {
		return OBJECT_MAPPER;
	}
	
	public static boolean isJSON(String url) {
		return StringUtils.isNotBlank(url) && url.indexOf("}") > -1 && url.indexOf("{") > -1;
	}

	public static void main(String[] args){
		String s ="{\"range\":19,\"open\":false}";
		Map<String,Object> m2 =  json2Map(s);
		System.out.println(m2.toString());
	}
}