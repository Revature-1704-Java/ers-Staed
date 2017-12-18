package com.staed.ers;

import com.staed.ers.dao.EmployeeDAO;

public class App {

	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		System.out.println(dao.getAllEmployee());
		
		System.out.println("But:");
		System.out.println(dao.getEmployee(2));
	}

}
