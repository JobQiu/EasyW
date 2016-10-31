package com.qcm.other.learnPPFB;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPPFB {
	public static void main(String[] args) {
		ApplicationContext a = new ClassPathXmlApplicationContext(
				"springmvc-servlet.xml");
		System.out.println("the system get the son1 as " + a.getBean("son1")
				+ " " + ((Son) a.getBean("son1")).getAge());
	}
}
