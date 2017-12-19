package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.beans.Employee;
import com.staed.ers.factory.EmployeeFactory;

class EmployeeFactoryTest {
	static EmployeeFactory ef;
	
	@BeforeAll
	public static void newInstance() {
		ef = new EmployeeFactory();
	}

	@DisplayName("EmployeeFactory: Test for Correct Employee Generation")
	@Test
	void employeeFactoryInitTest() {
		Employee mgr = ef.getManager(1, "fn", "ln", "un");
		assertTrue(mgr.isManager());
		assertTrue(mgr.getFirstName().equals("fn"));
		assertTrue(mgr.getLastName().equals("ln"));
		assertTrue(mgr.getUsername().equals("un"));
		
		Employee emp = ef.getEmployee(2, "nf", "nl", "nu", 0);
		assertFalse(emp.isManager());
		assertTrue(emp.getFirstName().equals("nf"));
		assertTrue(emp.getLastName().equals("nl"));
		assertTrue(emp.getUsername().equals("nu"));
		
		String expS = "Employee: {id:1, firstName:fn, lastName:ln, username:un, super:0, manager:true}";
		assertEquals(mgr.toString(), expS);
	}

}
