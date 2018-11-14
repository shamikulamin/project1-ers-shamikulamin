package com.revature.services;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementService {
	ReimbursementService currentImplmentation = new ReimbursementServiceImpl();
	
	List<Reimbursement> getAll(String S);
	
	int save(Reimbursement r);
	
	int updateStatus(Reimbursement r);
}
