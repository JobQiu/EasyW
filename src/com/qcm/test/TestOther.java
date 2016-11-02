package com.qcm.test;

import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.qcm.util.StringUtil;

public class TestOther {
	public static void main(String[] args) {
		String string = "{\"field\":0,\"work\":0,\"employment\":0,\"trade\":0,\"function\":0,\"dodge\":0,\"livelihood\":0,\"career\":0}";
		System.out.println(string);
		Map<String, Integer> re = (Map<String, Integer>) JSON.parse(string);
		for (Entry<String, Integer> e : re.entrySet()) {
			System.out.println(e.getKey());
		}
		String[] rr = { "123", "fe4" };
		System.out.println(StringUtil.stringArray2Mysql(rr));
	}
}
