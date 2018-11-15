package com.revature.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.Credential;
import com.revature.model.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.util.ResponseMapper;

public class ReimbursementController {
	private Logger log = Logger.getRootLogger();
	private ReimbursementService rs = ReimbursementService.currentImplmentation;
	private ObjectMapper om = new ObjectMapper();

	void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String method = req.getMethod();
		switch (method) {
		case "GET":
			processGet(req, resp);
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
	
	private void processGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "ERS";
		uri = uri.substring(context.length() + 2, uri.length());
		String[] uriArray = uri.split("/");
		System.out.println(Arrays.toString(uriArray));
		log.info("retreiving all reimbursements");
		
		System.out.println(uriArray[1]);
		
		if("getMan".equals(uriArray[1])) {
			List<Reimbursement> reimb = rs.getAllMan();
			ResponseMapper.convertAndAttach(reimb, resp);
		}
		else{
			List<Reimbursement> reimb = rs.getAll(uriArray[1]);
			ResponseMapper.convertAndAttach(reimb, resp);
		}
//		if (uriArray.length == 1) {
////			String role = (String) req.getSession().getAttribute("role");
////			if (!"ADMIN".equals(role)) {
////				resp.setStatus(403);
////				return;
////			} else {
//				
//		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		String context = "ERS";
		uri = uri.substring(context.length() + 2, uri.length());
		log.debug("URI: " + uri);
		if ("reimbursement".equals(uri)) {
			log.info("at reimbursement path");
		} else if ("reimbursement/save".equals(uri)) {
			log.info("attempting to save to db");
			Reimbursement r = om.readValue(req.getReader(), Reimbursement.class);
			rs.save(r);
			ResponseMapper.convertAndAttach(r, resp);
		
		} 
		else if ("reimbursement/update".equals(uri)) {
			log.info("attempting to update db");
			Reimbursement r = om.readValue(req.getReader(), Reimbursement.class);
			rs.updateStatus(r);
		}
		else if ("reimbursement/update/ticket".equals(uri)) {
			log.info("attempting to update ticket db");
			Reimbursement r = om.readValue(req.getReader(), Reimbursement.class);
			rs.updateReimb(r);
		}
		else {
			resp.setStatus(404);
			return;
		}
	}
	
}
