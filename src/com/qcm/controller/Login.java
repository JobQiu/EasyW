package com.qcm.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcm.dao.impl.UserDaoImpl;
import com.qcm.entity.User;
import com.qcm.util.CaptchaUtil;
import com.qcm.util.SendMailUtil;
import com.qcm.util.StringUtil;
import com.qcm.util.ValidationUtil;

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
		String subject = "Welcome to EW, " + user.getName();
		String content = "Hi,"
				+ user.getName()
				+ "<br/>Welcome to EW. Your password is "
				+ user.getPwd()
				+ ". If you have some problems or suggestion, please contact with me. <br/> Cheers ";
		SendMailUtil sendMailUtil = new SendMailUtil(user.getEmail(), subject,
				content);
		sendMailUtil.start();
		System.out.println(user.getPwd());
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

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CaptchaUtil.outputCaptcha(request, response);
	}

	@RequestMapping("checkUsernameExists")
	public @ResponseBody
	String checkUsernameExists(String username) {
		String info;
		if (StringUtil.isEmpty(username)) {
			info = "<span style='color:red'>FALSE</span>";
			return info;
		}
		User user = userDaoImpl.searchByName(username);
		if (user == null) {
			info = "<span style='color:blue'>TRUE</span>";
		} else {
			info = "<span style='color:red'>This username has been registered.</span>";
		}
		System.out.println(info);

		return info;
	}

	@RequestMapping("checkEmail")
	public @ResponseBody
	String checkEmail(String email) {
		String info;
		if (userDaoImpl.isEmailRegistered(email)) {
			info = "<span style='color:red'>This email has been registered.</span><a href='www.baidu.com'>Find my password.</a>";
		} else if (ValidationUtil.isEmail(email)) {
			info = "<span style='color:blue'>TRUE</span>";
		} else {
			info = "<span style='color:red'>FALSE</span>";
		}
		return info;
	}


}
