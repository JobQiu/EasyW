package com.qcm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.qcm.dao.IIgnoredWordDao;
import com.qcm.entity.IgnoredWord;
import com.qcm.entity.WordEntity;
import com.qcm.util.StringUtil;

public class IgnoredWordDaoImpl implements IIgnoredWordDao {

	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public boolean isIgnored(String word) {
		// TODO Auto-generated method stub
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		List<IgnoredWord> results = session.createQuery(
				"select distinct i from IgnoredWord i where word='" + word
						+ "'").list();
		transaction.commit();
		session.close();
		if (results.size() == 0) {
			return false;
		}
		return true;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] removeIgnored(String[] words) {
		// TODO Auto-generated method stub
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		List<IgnoredWord> results = session.createQuery(
				"select distinct i from IgnoredWord i where word in "
						+ StringUtil.stringArray2Mysql(words))
				.list();
		transaction.commit();
		session.close();
		ArrayList<String> w = new ArrayList<String>();
		for (IgnoredWord ignoredWord : results) {
			w.add(ignoredWord.getWord());
			// System.out.println(ignoredWord.getWord());
		}
		for (int i = 0; i < words.length; i++) {
			if (w.contains(words[i])) {
				words[i] = null;
			}
		}
		return words;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void ingoreWord(String word) {
		// TODO Auto-generated method stub
		IgnoredWord ignor = new IgnoredWord();
		ignor.setWord(word);
		
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		// insert a word into ignore tab
		List<IgnoredWord> is = session.createQuery(
				"select distinct i from IgnoredWord i where word='" + word
						+ "'").list();
		// if not existed, add it
		if(is.size()==0){
			session.save(ignor);
		}
		// delete the word from the word tab
		List<WordEntity> ws = session
				.createQuery(
						"select distinct w from WordEntity w where WORD='"
								+ word + "'").list();
		if (ws.size() != 0) {
			WordEntity we = ws.get(0);
			Integer id = we.getId();
			// find the variant version of this word and delete them
			List<WordEntity> ws2 = session.createQuery(
					"select distinct w from WordEntity w where WORD_PROTOTYPE="
							+ id).list();
			for (WordEntity wordEntity : ws2) {
				session.delete(wordEntity);
			}
			// delete itself
			session.delete(we);
		}
		transaction.commit();
		session.close();
	}

}
