package com.staed.ers.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.staed.ers.beans.Employee;
import com.staed.ers.factory.EmployeeFactory;

public class EmployeeDAO {
	public List<Employee> getAllEmployee() {
		List<Employee> empList = new ArrayList<>();
		PreparedStatement ps = null;
		
		try (Connection conn = getConnection()) {
			System.out.println("Connection: " + conn);
			String sql = "SELECT * FROM EMPLOYEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				empList.add(extractEmployeeResult(rs));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
		
		return empList;
	}
	
	public Employee getEmployee(int id) {
		Employee emp = null;
		PreparedStatement ps = null;
		
		try(Connection conn = getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				emp = extractEmployeeResult(rs);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return emp;
	}
	
	private Employee extractEmployeeResult(ResultSet rs) throws SQLException {
		EmployeeFactory ef = new EmployeeFactory();
		Employee emp = null;
		
		int eId = rs.getInt("EMPLOYEEID");
		String fName = rs.getString("FIRSTNAME");
		String lName = rs.getString("LASTNAME");
		String uname = rs.getString("USERNAME");
		int superId = rs.getInt("SUPERID");
		
		Boolean isMgr = false;
		if (rs.getInt("ISMANAGER") > 0)
			isMgr = true;
		
		if (isMgr)
			emp = ef.getManager(eId, fName, lName, uname);
		else
			emp = ef.getEmployee(eId, fName, lName, uname, superId);
		
		return emp;
	}

	private static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("src/main/resources/connection.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		System.out.println("Url: " + url + ", User: " + user + ", Pass: " + password);
		
		return DriverManager.getConnection(url, user, password);
	}
}
