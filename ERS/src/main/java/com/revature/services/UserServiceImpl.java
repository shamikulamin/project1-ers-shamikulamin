package com.revature.services;

import javax.servlet.http.HttpSession;

import com.revature.daos.UserDao;
import com.revature.dto.Credential;
import com.revature.model.Users;

public class UserServiceImpl implements UserService{
	private UserDao ud = UserDao.currentImplementation;

	@Override
	public boolean getUserLogin(Credential cred, HttpSession session) {
		Users u = ud.getUserLogin(cred.getUsername(), cred.getPassword());
		if (u != null) {
			session.setAttribute("role", u.getRoleId());
			return true;
		} 
		return false;
	}

	@Override
	public Users getUserInfo(String s) {
		return ud.getUserInfo(s);
	}

}
