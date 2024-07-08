package com.cheif.common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
	private static final String FILE_NAME = "dev.properties";
	private static Properties prop;
	
	private static Connection conn;
	
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		prop = new Properties();
		Reader reader = new FileReader(JDBCTemplate.class.getResource(FILE_NAME).getPath());
		prop.load(reader);
		String driverName = prop.getProperty("driverName");
		String url		  = prop.getProperty("url");
		String user		  = prop.getProperty("user");
		String password	  = prop.getProperty("password");
		
		if(conn == null || conn.isClosed()) {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		}


		return conn;
	}
}