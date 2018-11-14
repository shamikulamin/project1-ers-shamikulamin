package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.model.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{
	
	private ReimbursementDao rd = ReimbursementDao.currentImplementation;

	@Override
	public List<Reimbursement> getAll(String s) {
		return rd.getAll(s);
	}

	@Override
	public int save(Reimbursement r) {
		return rd.save(r);
	}

	@Override
	public int updateStatus(Reimbursement r) {
		// TODO Auto-generated method stub
		return rd.updateStatus(r);
	}

}
