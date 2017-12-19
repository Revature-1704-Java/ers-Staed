package com.staed.ers;

import java.sql.Date;

import com.staed.ers.beans.Employee;
import com.staed.ers.dao.EmployeeDAO;
import com.staed.ers.dao.ReimbursementDAO;

public class Service {
	EmployeeDAO empDAO;
	Employee curUser;
	
	ReimbursementDAO reimbDAO;
	
	public Service(EmployeeDAO empDAO, ReimbursementDAO reimbDAO) {
		this.empDAO = empDAO;
		this.reimbDAO = reimbDAO;
		this.curUser = null;
	}
	
	public boolean loggedIn() {
		return curUser != null ? true : false;
	}
	
	public boolean login(String user, String pass) {
		curUser = empDAO.employeeLogin(user, pass);
		if (curUser != null) {
			System.out.println("Login successful");
			return true;
		} else {
			System.out.println("Login unsuccessful");
			return false;
		}
	}
	
	public boolean logout() {
		if (curUser == null) {
			System.out.println("You weren't logged in to begin with.");
			return false;
		} else {
			curUser = null;
			System.out.println("You are now logged out");
			return true;
		}
	}
	
	public boolean viewAll() {
		if (curUser != null && curUser.isManager()) {
			System.out.println(reimbDAO.getAllReimbursement());
			return true;
		} else {
			System.out.println("You don't have permission to do that");
			return false;
		}
	}
	
	public boolean request(String dateString, String desc, float amt) {
		int eId = curUser.getId();
		int hId = curUser.getSuperId();
		if (curUser.getSuperId() == 0)
			hId = curUser.getId();
		Date rDate = new Date(java.sql.Date.valueOf(dateString).getTime());
		int success = reimbDAO.addReimbursement(eId, hId, rDate, desc, amt);
		
		if (success > 0) {
			System.out.println("Reimbursement Request Submitted.");
			return true;
		} else {
			System.out.println("Failed to submit reimbursement");
			return false;
		}
	}
	
	public Employee getUser() {
		return curUser;
	}
}
