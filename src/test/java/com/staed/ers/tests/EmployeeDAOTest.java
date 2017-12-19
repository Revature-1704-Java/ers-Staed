package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.beans.Employee;
import com.staed.ers.dao.EmployeeDAO;

class EmployeeDAOTest {
	static EmployeeDAO empDAO;
	
	@BeforeAll
	static void newInstance() {
		empDAO = new EmployeeDAO();
	}
	
	@DisplayName("EmployeeDAO: getAll")
	@Test
	void getAllEmployeeTest() {
		List<Employee> emps = empDAO.getAllEmployee();
		assertNotNull(emps);
		assertTrue(emps.size() > 0);
	}
	
	@DisplayName("EmployeeDAO: getOne")
	@Test
	void getEmployeeTest() {
		Employee emp = empDAO.getEmployee(1);
		assertNotNull(emp);
		assertEquals(1, emp.getId());
	}

}
