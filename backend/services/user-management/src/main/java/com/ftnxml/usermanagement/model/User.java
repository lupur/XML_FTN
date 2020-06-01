package com.ftnxml.usermanagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ftnxml.usermanagement.enums.AccountStatus;

public class User {
	private Long id;
	private String username;
	private String password;
	private String email;
	private String accessToken;
	private Role role;
	private Date registerDate;
	private AccountStatus accountStatus;
	
	public User() {
		super();
	}

	public User(Long id, String username, String password, String email, String accessToken, Role role, Date registerDate,
	        AccountStatus accountStatus) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.accessToken = accessToken;
		this.role = role;
		this.registerDate = registerDate;
		this.accountStatus = accountStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
}
