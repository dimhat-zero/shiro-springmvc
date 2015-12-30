package org.dimhat.example.web.form;

import javax.validation.constraints.Pattern;


/**
 * 登录表单
 * @author dimhat
 * @date 2015年12月30日 上午11:24:09
 * @version 1.0
 */
public class LoginForm {

	@Pattern(regexp="^[a-zA-Z_][\\w]{3,19}",message = "{user.username.error}")
	private String username;
	
	@Pattern(regexp="^[\\w]{3,20}",message="{user.password.error}")
	private String password;

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
	
	
}
