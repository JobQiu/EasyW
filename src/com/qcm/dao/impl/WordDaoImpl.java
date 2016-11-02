package com.qcm.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.qcm.dao.IWordDao;
import com.qcm.entity.WordEntity;
import com.qcm.util.StringUtil;
import com.qcm.util.SynonymUtil;

public class WordDaoImpl implements IWordDao {


	@Override
	public boolean insertWord(WordEntity word) {
		// TODO Auto-generated method stub
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		session.save(word);
		transaction.commit();
		session.close();
		return false;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"classpath:springmvc-servlet.xml");
		WordDaoImpl wordDaoImpl = applicationContext.getBean(WordDaoImpl.class,
				"wordDaoImpl");
		// WordEntity word = new WordEntity();
		// word.setWord("wonderful");
		//
		// word.setWord_synonym("dwefijwei");
		// wordDaoImpl.insertWord(word);
		// WordEntity word = wordDaoImpl.getWordEntityByWord("dew");
		// System.out.println(word.getId());
		System.out.println(wordDaoImpl.getSynonymByWord("any"));
	}
	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}

	@Override
	public WordEntity getWordById(Integer id) {
		// TODO Auto-generated method stub
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		WordEntity w = session.get(WordEntity.class, id);
		transaction.commit();
		session.close();
		return w;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WordEntity> getWordByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		String idStrings = Arrays.toString(ids);
		idStrings = idStrings.replace('[', '(');
		idStrings = idStrings.replace(']', ')');
		System.out.println("this is at test" + idStrings);
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		List<WordEntity> ws = session.createQuery(
				"select distinct w from WordEntity w where id in " + idStrings)
				.list();
		transaction.commit();
		session.close();
		return ws;
		// return null;
	}

	@Override
	public LinkedHashMap<String, Integer> getSynonymById(Integer id) {
		WordEntity wordEntity = getWordById(id);
		if (wordEntity == null) {
			return null;
		}
		String synonyms = wordEntity.getWord_synonym();
		// System.out.println(synonyms);
		LinkedHashMap<String, Integer> res = StringUtil
				.string2LinkedHashMap(synonyms);
		// Map<String, Integer> re = (Map<String, Integer>)
		// JSON.parse(synonyms);
		return res;
	}

	@Override
	public Integer getIdByWord(String word) {
		// TODO Auto-generated method stub
		WordEntity wordEntity = getWordEntityByWord(word);
		if (wordEntity == null)
			return -1;
		return wordEntity.getId();
	}

	@SuppressWarnings("unchecked")
	public WordEntity getWordEntityByWord(String word) {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		List<WordEntity> words = session.createQuery(
				"select distinct w from WordEntity w where WORD= '" + word
						+ "'").list();
		if (words.size() == 0) {
			return null;
		}
		WordEntity wordEntity = words.get(0);
		transaction.commit();
		session.close();
		return wordEntity;
	}

	private SynonymUtil insertSyno(String word) {
		SynonymUtil s = new SynonymUtil(word);
		s.setWordE(new WordEntity());
		s.setWordDaoImpl(this);
		Thread thread = new Thread(s);
		thread.run();
		System.out.println("xingxi" + s.getInfo());
		return s;
	}
	@Override
	public LinkedHashMap<String, Integer> getSynonymByWord(String word) {
		WordEntity wordEntity = getWordEntityByWord(word);
		if (wordEntity == null) {
			SynonymUtil s = insertSyno(word);
			if (s.getInfo().equals("There is no this word, please check.")) {
				return null;
			}
		}
		wordEntity = getWordEntityByWord(word);
		String synonyms = wordEntity.getWord_synonym();
		// System.out.println(synonyms);
		LinkedHashMap<String, Integer> res = StringUtil
				.string2LinkedHashMap(synonyms);
		// Map<String, Integer> re = (Map<String, Integer>)
		// JSON.parse(synonyms);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WordEntity> getWordByWords(List<String> words) {
		// TODO Auto-generated method stub
		String[] wordss = words.toArray(new String[words.size()]);
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		List<WordEntity> results = session.createQuery(
				"select distinct w from WordEntity w where WORD in "
						+ StringUtil.stringArray2Mysql(wordss)).list();
		// System.out.println(StringUtil.stringArray2Mysql(words));
		transaction.commit();
		// System.out.println(results.size());
		List<String> have = new ArrayList<String>();
		for (WordEntity wordEntity : results) {
			have.add(wordEntity.getWord());
		}
		for (String string : words) {
			if (!have.contains(string)) {
				insertSyno(string);
			}
		}
		transaction = session.beginTransaction();
		results = session.createQuery(
				"select distinct w from WordEntity w where WORD in "
						+ StringUtil.stringArray2Mysql(wordss)).list();
		// System.out.println(StringUtil.stringArray2Mysql(words));
		transaction.commit();
		session.close();
		return results;
	}

}
