package com.staed.ers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() {
		try {
			Properties prop = new Properties();
			InputStream in;
			in = new FileInputStream("src/main/resources/connection.properties");
			prop.load(in);
			
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			//System.out.println("Url: " + url + ", User: " + user + ", Pass: " + password);
			
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
