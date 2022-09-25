package com.laptrinhjavaweb.utils;

import java.util.Map;

public class MapUtils {
	
//	public static  <T> T  getObject (Map<String, Object> params, String key,  Class<T> tClass){
//		Object object = params.getOrDefault(key, null);
//		if (object != null) {
//			if (tClass.getTypeName().equals("java.lang.Long")) {
//				object = object != " " ? Long.valueOf(object.toString()) : null ;
//			} else if(tClass.getTypeName().equals("java.lang.Integer")) {
//				  object = object != " " ? Integer.parseInt(object.toString()) : null;
//			} else if(tClass.getTypeName().equals("java.lang.String")) {
//				  object = object.toString();
//			} 	
//			return  tClass.cast(object);
//		}
//		return null;
//	}
	
	public static  <T> T  getObject (Map<String, String> params, String key,  Class<T> tClass){
		String str = params.getOrDefault(key, null);
		Object object = null ;
		if (str != null) {
			if (tClass.getTypeName().equals("java.lang.Long")) {
				object = str != " " ? Long.valueOf(str) : null ;
			} else if(tClass.getTypeName().equals("java.lang.Integer")) {
				  object = str != " " ? Integer.parseInt(str.toString()) : null;
			} else if(tClass.getTypeName().equals("java.lang.String")) {
				  object = str;
			} 	
			return  tClass.cast(object);
		}
		return null;
	}
}
