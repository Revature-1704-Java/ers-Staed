package com.staed.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.staed.ers.ConnectionUtil;
import com.staed.ers.beans.Employee;
import com.staed.ers.factory.EmployeeFactory;

public class EmployeeDAO {
	public List<Employee> getAllEmployee() {
		List<Employee> empList = new ArrayList<>();
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			//System.out.println("Connection: " + conn);
			String sql = "SELECT * FROM EMPLOYEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				empList.add(extractEmployee(rs));
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
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				emp = extractEmployee(rs);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return emp;
	}
	
	private Employee extractEmployee(ResultSet rs) throws SQLException {
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
}
