package com.laptrinhjavaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class ConnectionUtils {
//	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
//	static final String USER = "root";
//	static final String PASS = "123456789";
	
	@Value("${spring.datasource.url}")
	private static String dbUrl;
	
	@Value("${spring.datasource.username}")
	private static String userName;
	
	@Value("${spring.datasource.password}")
	private static String passWord;
	
	public static Connection getConnection () {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl,userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
