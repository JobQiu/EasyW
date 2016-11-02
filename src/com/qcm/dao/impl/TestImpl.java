package com.qcm.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestImpl {
	public static void main(String[] args) {
		ApplicationContext a = new ClassPathXmlApplicationContext(
				"springmvc-servlet.xml");
		// WordDaoImpl w = a.getBean(WordDaoImpl.class, "wordDaoImpl");
		// Map<String, Integer> test = w.getSynonymByWord("water");
		// for (Entry<String, Integer> iterable_element : test.entrySet()) {
		// System.out.println(iterable_element.getKey() + "="
		// + iterable_element.getValue());
		// }
		IgnoredWordDaoImpl w = a.getBean(IgnoredWordDaoImpl.class,
				"ignoredWordDaoImpl");
		String[] words = { "wonderful", "me", "the" };
		words = w.removeIgnored(words);
		for (String string : words) {
			System.out.println(string);
		}
		// String synonyms = w.getSynonymById(2);
		// System.out.println(synonyms);
		// System.out.println(w.getWordById(1).getWord());
		// Integer[] ids = { 1, 2, 3 };
		// System.out.println(Arrays.toString(ids));
		// List<WordEntity> www = w.getWordByIds(ids);
		// for (WordEntity wordEntity : www) {
		// System.out.println(wordEntity.getWord());
		// }
		//
		// Map<Integer, Integer> test = new HashMap<Integer, Integer>();
		// test.put(1, 1);
		// test.put(2, 1);
		// test.put(3, 1);
		// test.put(4, 1);
		//
		// String testJson = JSON.toJSONString(test);
		// System.out.println(testJson);
	}
}
