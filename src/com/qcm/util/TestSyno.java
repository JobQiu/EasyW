package com.qcm.util;

import java.util.LinkedHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;

public class TestSyno {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"classpath:springmvc-servlet.xml");

		SynonymUtil s = applicationContext.getBean(SynonymUtil.class,
				"synonymUtil");
		s.setWord("job");
		Thread t = new Thread(s);
		t.start();
		// SynonymUtil s2 = new SynonymUtil("bad");
		// Thread t2 = new Thread(s2);
		// t2.start();
		// for (int i = 0; i < 2000; i++) {
		// System.out.println(i);
		// }

		String[] rr = { "fantastic", "remarkable", "outstanding" };
		LinkedHashMap lgm = new LinkedHashMap<String, Integer>();
		String jsonn;
		System.out.println(jsonn = JSON.toJSONString(rr));
		jsonn = StringUtil.string2Mysql(jsonn);
		System.out.println(jsonn);

	}
}
