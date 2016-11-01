package com.qcm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.qcm.dao.IUserDao;
import com.qcm.entity.User;
import com.qcm.util.StringUtil;

public class UserDaoImpl implements IUserDao {
	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User searchByName(String username) {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(username)) {
			return null;
		}
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> users = session
				.createQuery(
						"select distinct u from User u where USER_NAME = :username")
				.setString("username", username).list();
		transaction.commit();
		session.close();
		if (users.size() == 0)
			return null;
		else {
			return users.get(0);
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"classpath:springmvc-servlet.xml");
		UserDaoImpl userDaoImpl = applicationContext.getBean(UserDaoImpl.class,
				"userDaoImpl");
		User result = userDaoImpl.searchByName("qwe");
		System.err.println(result.getPwd());
		User reg = new User();
		reg.setEmail("qcm327194@163.com");
		reg.setName("qe32qc5m");
		reg.setPwd("123");
		System.out.println("register successfully "
				+ userDaoImpl.registerUser(reg));
		User user = new User();

		user.setPwd("rwq");
		System.out.println(userDaoImpl.checkUser(user));
		System.out
				.println(userDaoImpl.isEmailRegistered("qcm32771787@163.com"));

	}
	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		// check some thing
		if (searchByName(user.getName()) != null)
			return false;
		if (!user.canRegister())
			return false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public boolean checkUser(User user) {
		// TODO Auto-generated method stub
		User aUser = searchByName(user.getName());
		if (aUser == null || (!aUser.getPwd().equals(user.getPwd())))
			return false;
		return true;
	}

	@Override
	public boolean isEmailRegistered(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<User> users = session.createQuery(
				"select distinct u from User u where email='" + email + "'")
				.list();
		transaction.commit();
		session.close();
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

}
