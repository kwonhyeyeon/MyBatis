package com.spring.client.login.vo;

public class LoginVO extends LoginHistory {
	private String userId = ""; // user아이디
	private String userPw = ""; // user비밀번호
	private String userName = ""; // user이름
	// field 설정

	public LoginVO() {
	}

	public LoginVO(String userId, String userPw, String userName) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
	}

	// getter setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// tostring override
	@Override
	public String toString() {
		return "LoginVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", getIdx()=" + getIdx()
				+ ", getRetry()=" + getRetry() + ", getLastFailedLogin()=" + getLastFailedLogin()
				+ ", getLastSuccessedLogin()=" + getLastSuccessedLogin() + ", getClientIp()=" + getClientIp() + "]";
	}
}