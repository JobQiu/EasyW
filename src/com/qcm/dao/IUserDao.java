package com.qcm.dao;

import com.qcm.entity.User;

public interface IUserDao {
	/**
	 * search User by username, if user exists, return the id, else return -1
	 * 
	 * @author JobQ
	 * */
	User searchByName(String username);

	/**
	 * register the user, return false if failed
	 * 
	 * @author JobQ
	 * */
	boolean registerUser(User user);

	/**
	 * check the user name and his password
	 * 
	 * @author JobQ
	 * */
	boolean checkUser(User user);
	/**
	 * check if the email has been registered!
	 * @author JobQ
	 * */
	boolean isEmailRegistered(String email);
}
