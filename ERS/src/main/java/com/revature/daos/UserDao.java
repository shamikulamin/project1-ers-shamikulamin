package com.revature.daos;

import com.revature.model.Users;

public interface UserDao {
	UserDao currentImplementation = new UserDaoJdbc();
	
	Users getUserLogin(String userName, String pass);
	Users getUserInfo(String s);
}
