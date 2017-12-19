package com.staed.ers;

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
	
	public void login(String user, String pass) {
		curUser = empDAO.employeeLogin(user, pass);
		if (curUser != null)
			System.out.println("Login successful");
		else
			System.out.println("Login unsuccessful");
		
		System.out.println(curUser);
	}
	
	public void logout() {
		if (curUser == null)
			System.out.println("You weren't logged in to begin with.");
		else {
			curUser = null;
			System.out.println("You are now logged out");
		}
	}
	
	public void viewAll() {
		if (curUser != null && curUser.isManager())
			System.out.println(reimbDAO.getAllReimbursement());
		else
			System.out.println("You don't have permission to do that");
	}
}
