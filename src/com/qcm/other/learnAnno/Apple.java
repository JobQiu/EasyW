package com.qcm.other.learnAnno;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller("ttt")
public class Apple {

	public static void main(String[] args) {
		ApplicationContext a = new ClassPathXmlApplicationContext(
				"springmvc-servlet.xml");
		System.out.println("---------------"
				+ Arrays.toString(a.getBeanDefinitionNames()));
	}
}
