package com.staed.ers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() {
		InputStream in = null;
		try {
			in = new FileInputStream("src/main/resources/connection.properties");
			return _getConnection(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Connection _getConnection(InputStream in) throws IOException, SQLException {
		Properties prop = new Properties();
		prop.load(in);
			
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
			
		return DriverManager.getConnection(url, user, password);
	}
}
