package com.staed.ers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.staed.ers.beans.Employee;
import com.staed.ers.factory.EmployeeFactory;

public class EmployeeDAO extends DAO {
	public List<Employee> getAllEmployee() {
		String sql = "SELECT * FROM EMPLOYEE";
		return resultIterator(prepareStatement(sql));
	}

	public Employee getEmployee(int id) {
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEEID = ?";
		PreparedStatement ps = prepareStatement(sql);
		try {
			ps.setInt(1, id);
		} catch (SQLException e) {
			e.getMessage();
		}
		List<Employee> res = resultIterator(ps);
		return res.isEmpty() ? null : res.get(0);
	}
	
	public Employee employeeLogin(String user, String pass) {
		String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASS = ?";
		PreparedStatement ps = prepareStatement(sql);
		try {
			ps.setString(1, user);
			ps.setString(2, pass);
		} catch (SQLException e) {
			e.getMessage();
		}
		List<Employee> res = resultIterator(ps);
		return res.isEmpty() ? null : res.get(0);
	}
	
	@SuppressWarnings("unchecked")
	List<Employee> resultIterator(PreparedStatement ps) {
		return super.resultIterator(ps);
	}

	@SuppressWarnings("unchecked")
	Employee extractRow(ResultSet rs) {
		try {
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
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
	}
}
