package com.qcm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qcm.dao.ISentenceDao;
import com.qcm.entity.WordEntity;
import com.qcm.util.StringUtil;

public class SentenceDaoImpl implements ISentenceDao {
	@Resource
	private WordDaoImpl wordDaoImpl;
	@Resource
	private IgnoredWordDaoImpl ignoredWordDaoImpl;

	@Override
	public List<String> divideSentence(String sentence) {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(sentence))
			return null;
		String[] results = sentence.split(" ");
		results = ignoredWordDaoImpl.removeIgnored(results);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < results.length; i++) {
			if (results[i] != null) {
			results[i] = StringUtil.dealWord(results[i]);
				result.add(results[i]);
			}
		}
		return result;
	}


	public IgnoredWordDaoImpl getIgnoredWordDaoImpl() {
		return ignoredWordDaoImpl;
	}

	public void setIgnoredWordDaoImpl(IgnoredWordDaoImpl ignoredWordDaoImpl) {
		this.ignoredWordDaoImpl = ignoredWordDaoImpl;
	}

	public static void main(String[] args) {
		String string = "I want to test big letter later";
		ApplicationContext a = new ClassPathXmlApplicationContext(
				"springmvc-servlet.xml");

		List<String> reStrings = a.getBean(SentenceDaoImpl.class)
				.divideSentence(string);
		WordDaoImpl wordDaoImpl = a.getBean(WordDaoImpl.class, "wordDaoImpl");
		List<WordEntity> wordEntities = wordDaoImpl.getWordByWords(reStrings);
		for (WordEntity wordEntity : wordEntities) {
			System.out.println(wordEntity.getLhm());
		}
		// sentenceDaoImpl.getSynoByStrArray(reStrings);
		for (String string2 : reStrings) {
			System.out.println(string2);
		}
		// test ignore
		IgnoredWordDaoImpl i = a.getBean(IgnoredWordDaoImpl.class);
		i.ingoreWord("be");
	}

	public WordDaoImpl getWordDaoImpl() {
		return wordDaoImpl;
	}

	public void setWordDaoImpl(WordDaoImpl wordDaoImpl) {
		this.wordDaoImpl = wordDaoImpl;
	}
}
