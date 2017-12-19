package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.beans.Employee;

class EmployeeTest {
	Employee emp;
	
	@BeforeEach
	void newInstance() {
		emp = new Employee(1, "d", "b", "gg", 1, false);
	}

	@DisplayName("Employee: Getters and Setters")
	@Test
	void setHandlerTest() {
		assertEquals("d", emp.getFirstName());
		emp.setFirstName("s");
		assertEquals("s", emp.getFirstName());
		
		assertEquals("b", emp.getLastName());
		emp.setLastName("kk");
		assertEquals("kk", emp.getLastName());
		
		assertEquals("gg", emp.getUsername());
		emp.setUsername("uu");
		assertEquals("uu", emp.getUsername());
		
		assertEquals(1, emp.getSuperId());
		emp.setSuperId(0);
		assertEquals(0, emp.getSuperId());
	}

}
