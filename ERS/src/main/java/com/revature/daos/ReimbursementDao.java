package com.revature.daos;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementDao {
	ReimbursementDao currentImplementation = new ReimbursementDaoJdbc();
	
	List<Reimbursement> getAll(String s);
	
	int save(Reimbursement r);
	
	int updateStatus(Reimbursement r);
}
