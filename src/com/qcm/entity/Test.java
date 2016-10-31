package com.qcm.entity;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Test {
	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"classpath:springmvc-servlet.xml");

		Test test = applicationContext.getBean(Test.class, "test");
		Session session = test.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		// Admin admin = new Admin();
		// admin.setAdminName("qcmmmm");
		// admin.setAdminPassword("123");
		// session.save(admin);
		User user = new User();
		user.setEmail("qcm3771787@136.com");
		user.setName("qcm");
		user.setPwd("123");
		session.save(user);

		transaction.commit();
		session.close();
	}
}
