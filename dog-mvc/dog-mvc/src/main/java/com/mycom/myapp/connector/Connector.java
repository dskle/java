package com.mycom.myapp.connector;

import java.sql.*;

public class Connector {
	private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/dog_management?useSSL=false&serverTimezone=UTC";
	private static String USER_ID = "root";
	private static String PASSWORD = "rkxk";

	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName(DRIVER_NAME);

			con = DriverManager.getConnection(URL, USER_ID, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}

		return con;
	}
}
