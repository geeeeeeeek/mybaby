package com.xqs.mybaby.utils;



public class StringUtil {

	/**
	 * 字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isNullOrEmpty(str);
	}

	private static boolean isNullOrEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}





}

