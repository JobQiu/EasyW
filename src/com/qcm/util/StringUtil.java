package com.qcm.util;

import java.util.Arrays;
import java.util.LinkedHashMap;

import com.qcm.constant.Constant;

/**
 * @author JobQ
 * */
public class StringUtil {
	
	/**
	 * 判断字符串是否为null，空格，""空字符串
	 * @param str
	 * @return 
	 */
	public static boolean isEmpty(String str){
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * String转int
	 * @param str
	 * @return
	 */
	public static int stringToInt(String str){
		int integer = -1;
		try{
			integer = Integer.parseInt(str);
		} catch (NumberFormatException e){
			System.out.println("数值转换异常！！");
		}
		return integer;
	}

	/**
	 * string to mysql
	 * */
	public static String string2Mysql(String string) {
		string = string.replace("[", "\\[");
		string = string.replace("]", "\\]");
		string = string.replace("\"", "\\\"");
		return string;
	}

	public static String stringArray2Mysql(String[] words) {
		String result = Arrays.toString(words);
		result = result.replace("[", "('");
		result = result.replace(",", "','");
		result = result.replace("]", "')");
		result = result.replace(" ", "");
		return result;
	}
	/**
	 * String to linkedhashmap<string, integer>
	 * */
	public static LinkedHashMap<String, Integer> string2LinkedHashMap(
			String string) {
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		string = string.replace('{', ' ');
		string = string.replace('}', ' ');
		string = string.replace('"', ' ');
		// System.out.println(string);
		String[] strings = string.split(",");
		for (String string2 : strings) {
			// System.out.println(string2);
			int value = stringToInt(string2.split(":")[1].trim().toString());
			result.put(string2.split(":")[0].trim(), value);
		}
		return result;
	}

	public static void main(String[] args) {
		String string = "{\"field\":0,\"work\":0,\"employment\":0,\"trade\":0,\"function\":0,\"dodge\":0,\"livelihood\":0,\"career\":0}";
		// LinkedHashMap<String, Integer> hhHashMap =
		// string2LinkedHashMap(string);
		// for (Entry<String, Integer> string2 : hhHashMap.entrySet()) {
		// System.out.println(string2.getKey() + "=" + string2.getValue());
		// }
		System.out.println(dealWord(string));
	}
	public static String dealWord(String word) {
		for (String s : Constant.FILTER_CHAR) {
			word = word.replace(s, "");
		}
		return word;
	}
}

