package com.lm.web.entity.vo;

/**
 * 
 * 【这里用一句话描述这个方法的作用】
 * 
 * @author ShenZiYang
 * @date 2018年1月30日 下午4:38:56
 */
public class LoginVO {

	private String username;
	private String password;
	private String captcha;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
