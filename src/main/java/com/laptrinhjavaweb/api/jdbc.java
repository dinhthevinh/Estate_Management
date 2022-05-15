package com.laptrinhjavaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.laptrinhjavaweb.emyeuanh.BuildingEmyeuanh;
import com.laptrinhjavaweb.utils.StringUtils;

public class jdbc {
	static final String DB_URL = "jdbc:mysql://localhost:3306/javasql22022";
	static final String USER = "root";
	static final String PASS = "123456789";
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
//			StringBuilder sql = new StringBuilder("select * from building "); 
//			rs = stmt.executeQuery(sql.toString());
			// Extract data from result set
			System.out.println("succes");
//			BuildingEmyeuanh buildingEmyeuanh = new BuildingEmyeuanh();
//			while (rs.next()) {
//				// Retrieve by column name
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn!= null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
