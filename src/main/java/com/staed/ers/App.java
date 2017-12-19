package com.staed.ers;

import com.staed.ers.dao.EmployeeDAO;
import com.staed.ers.dao.ReimbursementDAO;

public class App {

	public static void main(String[] args) {
		EmployeeDAO empDao = new EmployeeDAO();
		System.out.print("All: ");
		System.out.println(empDao.getAllEmployee());
		
		System.out.print("\nSingle: ");
		System.out.println(empDao.getEmployee(2));
		
		ReimbursementDAO reimbDao = new ReimbursementDAO();
		System.out.print("\nAll Reimbursement: ");
		System.out.println(reimbDao.getAllReimbursement());
		
		System.out.print("\nSingle: ");
		System.out.println(reimbDao.getReimbursement(1));
	}

}
