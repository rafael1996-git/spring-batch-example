package com.eglobal.encryptpassword.models;

public class Dto_ConnectionPool {

	private String url;
	private String user;
	private String pass;
	private String driver;
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Dto_ConnectionPool() {
		super();
		// TODO Auto-generated constructor stub
	}
}
