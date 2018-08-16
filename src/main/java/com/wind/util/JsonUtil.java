package com.wind.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * @Title: JsonUtil
 * @Package com.wind.util
 * @Description: json工具
 * @author huanghy
 * @date 2018/8/16 17:49
 * @version V1.0
 */
public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static Gson gson;

	/**
	 * 实例化 gson
	 * @return
	 */
	private static Gson getInstance(){
		if(gson == null){
			gson = new Gson();
		}
		return gson;
	}

	/**
	 * java对象序列化为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return getInstance().toJson(obj);
	}

	/**
	 * 格式化json字符串
	 * @param jsonStr
	 * @return
	 */
	public static String format(String jsonStr){
		JsonParser parser = new JsonParser();
		JsonElement ele = parser.parse(jsonStr);
		return new GsonBuilder().setPrettyPrinting().create().toJson(ele);
	}

	/**
	 * 判断是否是json字符串
	 * @param jsonStr
	 * @return
	 */
	public static boolean isJson(String jsonStr){
		boolean jsonFlag;
		try {
			new JsonParser().parse(jsonStr).getAsJsonObject();
			jsonFlag = true;
		} catch (Exception e) {
			logger.warn("json string error:" + jsonStr, e);
			jsonFlag = false;
		}
		return jsonFlag;
	}

	/**
	 * json字符串反序列化为java对象
	 * @param jsonStr
	 * @param c
	 * @param <T>
	 * @return
	 */
	public static <T> T toBean(String jsonStr, Class<T> c){
		return getInstance().fromJson(jsonStr, c);
	}

	/**
	 * json数组字符串反序列化为java List
	 * @param jsonStr
	 * @param typeToken
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toList(String jsonStr, TypeToken<List<T>> typeToken){
		return getInstance().fromJson(jsonStr, typeToken.getType());
	}

}
