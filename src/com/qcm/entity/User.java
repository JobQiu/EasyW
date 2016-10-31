package com.qcm.entity;

import com.qcm.util.PwdSecurityUtil;
import com.qcm.util.StringUtil;

public class User {

	private int id;

	private String name;

	private String pwd;

	private String email;

	public void encryptPwd() {
		this.pwd = PwdSecurityUtil.transMd5(pwd, 5);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * to test if the user is qualified to register
	 * 
	 * @author JobQ
	 * */
	public boolean canRegister() {
		if (StringUtil.isEmpty(name) || StringUtil.isEmpty(pwd)
				|| StringUtil.isEmpty(email)) {
			return false;
		}
		return true;
	}


}
