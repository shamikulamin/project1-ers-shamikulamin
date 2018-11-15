package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DispatcherServlet extends HttpServlet{

	private Logger log = Logger.getRootLogger();
	private UserController uc = new UserController();
	private ReimbursementController rc = new ReimbursementController();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "http://villian-pub.s3-website.us-east-2.amazonaws.com/");
//		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		
		if (req.getMethod().equals("OPTIONS")){
			return;
		}
		
		String uri = req.getRequestURI();
		String context = "ERS";
		log.debug(uri);
		uri = uri.substring(context.length() + 2, uri.length());
		log.debug("request made with uri: " + uri);
		if (uri.startsWith("users")) {
			log.debug("here1");
			uc.process(req, resp);
		} else if (uri.startsWith("reimbursement")) {
			rc.process(req, resp);
		} else {
			resp.setStatus(404);
		}
	}

	
}
