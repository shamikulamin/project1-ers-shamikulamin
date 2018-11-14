package com.revature.services;

import javax.servlet.http.HttpSession;

import com.revature.dto.Credential;
import com.revature.model.Users;

public interface UserService {
	UserService currentImplementation = new UserServiceImpl();
	
	boolean getUserLogin(Credential cred, HttpSession session);
	Users getUserInfo(String s);
}
