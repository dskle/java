package com.mycom.myapp.user;

import javax.validation.constraints.*;

public class UserData {
	@NotNull
	@Size(min=1, max=20, message="id는 1글자에서 20글자 사이입니다.")
	private String user_id;
	@NotNull
	@Size(min=4, message="비밀번호 4글자 이상을 입력하십시오")
	private String password;

	public UserData() {
		
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
