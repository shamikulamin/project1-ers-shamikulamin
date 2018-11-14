package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoJdbc implements ReimbursementDao {

	@Override
	public List<Reimbursement> getAll(String s) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT reimbT.reimb_id, reimbT.reimb_amount, reimbT.reimb_submitted, reimbT.reimb_resolved,\r\n" + 
					"	   reimbT.reimb_description, reimbT.reimb_receipt, ers_users.ers_username, resolver.ers_username AS reimb_resolver, \r\n" + 
					"	   ers_reimbursement_status.reimb_status, ers_reimbursement_type.reimb_type \r\n" + 
					"	   FROM ers_reimbursement reimbT\r\n" + 
					"	   JOIN ers_users ON ers_users_id = reimbT.reimb_author\r\n" + 
					"	   JOIN ers_users resolver ON resolver.ers_users_id = reimbT.reimb_resolver\r\n" + 
					"	   JOIN ers_reimbursement_status ON ers_reimbursement_status.reimb_status_id = reimbT.reimb_status_id\r\n" + 
					"	   JOIN ers_reimbursement_type ON reimbT.reimb_type_id = ers_reimbursement_type.reimb_type_id\r\n" + 
					"	   WHERE ers_users.ers_username = ? ORDER BY reimbT.reimb_id DESC");
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimb = new ArrayList<>();
			while(rs.next()) {
				reimb.add(new Reimbursement(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4),rs.getString(5), 
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 0, 0, 0, 0));
			}
			System.out.println(reimb);
			return reimb;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int save(Reimbursement r) {
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
	    String subTime = r.getSubmittedTime();
	    String resTime = r.getResolved();
	    LocalDateTime submittedDateTime = LocalDateTime.parse(subTime, formatter);
	    LocalDateTime resolvedDateTime = null;
	    
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description," + 
					"reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)" + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, r.getAmount());
			ps.setObject(2, submittedDateTime);
			ps.setObject(3, resolvedDateTime);
			ps.setString(4, r.getDescription());
			ps.setString(5, r.getReceipt());;
			ps.setInt(6, r.getAuthor());
			ps.setInt(7, 1);
			ps.setInt(8, r.getReimbStatusId());
			ps.setInt(9, r.getReimbTypeId());
			ps.executeUpdate();
			return 1;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateStatus(Reimbursement r) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("UPDATE ers_reimbursement SET reimb_status_id = ? "
					+ "WHERE reimb_id = ?");
			ps.setInt(1, r.getReimbStatusId());
			ps.setInt(2, r.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
