package com.revature.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.Credential;
import com.revature.model.Reimbursement;
import com.revature.model.Response;
import com.revature.model.Users;
import com.revature.services.UserService;
import com.revature.util.ResponseMapper;

public class UserController {
	private Logger log = Logger.getRootLogger();
	private UserService us = UserService.currentImplementation;
	private ObjectMapper om = new ObjectMapper();
	
	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		switch (method) {
		case "GET":
			//processGet(req, resp);
			break;
		case "POST":
			processPost(req, resp);
			break;
		case "OPTIONS":
			return;
		default:
			resp.setStatus(404);
			break;
		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		String uri = req.getRequestURI();
		String context = "ERS";
		uri = uri.substring(context.length() + 2, uri.length());
		log.debug("URI: " + uri);
		if ("users".equals(uri)) {
			log.info("saving new user");
		} else if ("users/login".equals(uri)) {
			log.info("attempting to log in");
			Credential cred = om.readValue(req.getReader(), Credential.class);
			if(!us.getUserLogin(cred, req.getSession())) {
				resp.setStatus(403);
				return;
			}
			
			Users u = us.getUserInfo(cred.getUsername());
			if(u.getRoleId()==1)
				req.getSession().setAttribute("Role", "Employee");
			else if(u.getRoleId()==2)
				req.getSession().setAttribute("Role", "Manager");
			ResponseMapper.convertAndAttach(u, resp);
			return;
		} 
		else if("users/profile".equals(uri)) {
			String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			test = test.replaceAll(".*:", "");
			test = test.replaceAll("\"", "");
			test = test.replaceAll("}", "");
			System.out.println(test.replaceAll(".*:", ""));
			Users u = us.getUserInfo(test);
			ResponseMapper.convertAndAttach(u, resp);
		}
		else if ("users/logout".equals(uri)) {
			System.out.println("logging out user");
			req.getSession().invalidate();
			resp.setStatus(200);
		}
		else {
			resp.setStatus(404);
			return;
		}
	}
}
