package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Users;
import com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao{

	@Override
	public Users getUserLogin(String userName, String pass) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users WHERE ers_username = ?  AND ers_password = ?");
			ps.setString(1, userName);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}

	@Override
	public Users getUserInfo(String s) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users WHERE ers_username = ?");
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}
	
	private Users extractFromResultSet(ResultSet rs) throws SQLException {
		return new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
	}

}
