package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("db_url");
		String port = System.getenv("db_port");
		String dbName = System.getenv("db_name");
		String dbSchema = System.getenv("db_schema");
		String username = System.getenv("db_username");
		String password = System.getenv("db_password");

		String dataSource = "jdbc:postgresql://" + url + ":" + port + "/" + dbName + "?currentSchema=" + dbSchema;

		return DriverManager.getConnection(dataSource, username, password);
	}
}
