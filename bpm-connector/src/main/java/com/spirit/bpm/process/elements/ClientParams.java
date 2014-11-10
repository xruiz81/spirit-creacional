package com.spirit.bpm.process.elements;

import java.io.Serializable;

public class ClientParams implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user = "admin";
	private String password = "bpm";

	public ClientParams() {
	}

	public ClientParams(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
