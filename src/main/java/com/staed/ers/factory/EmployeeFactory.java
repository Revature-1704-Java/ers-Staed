package com.staed.ers.factory;

import com.staed.ers.beans.Employee;

public class EmployeeFactory {
	public Employee getEmployee(int id, String firstName, String lastName, String username, int superId) {			
		return new Employee(id, firstName, lastName, username, superId, false);
	}
	
	public Employee getManager(int id, String firstName, String lastName, String username) {
		return new Employee(id, firstName, lastName, username, 0, true);
	}

}
