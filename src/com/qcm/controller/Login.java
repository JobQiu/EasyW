package com.qcm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcm.dao.impl.UserDaoImpl;
import com.qcm.entity.User;

@Controller
public class Login {
	@Resource
	private UserDaoImpl userDaoImpl;

	@RequestMapping("/login")
	public String login(User user) {
		user.encryptPwd();
		System.out.println(userDaoImpl.searchByName(user.getName()));
		System.out.println(user.getName());
		if (userDaoImpl.checkUser(user))
			return "hello";
		else {
			return "fail";
		}
	}

	@RequestMapping("/register")
	public String register(User user) {
		user.encryptPwd();
		if (userDaoImpl.registerUser(user))
			return "hello";
		else {
			return "fail";
		}
	}

	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
}
