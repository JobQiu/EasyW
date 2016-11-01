package com.qcm.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.qcm.dao.IWordDao;
import com.qcm.entity.WordEntity;

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
		WordEntity word = new WordEntity();
		word.setWord("wonderful");

		word.setWord_synonym("dwefijwei");
		wordDaoImpl.insertWord(word);

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
}
