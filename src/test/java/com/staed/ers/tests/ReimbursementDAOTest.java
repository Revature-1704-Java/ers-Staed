package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.beans.Employee;
import com.staed.ers.beans.Reimbursement;
import com.staed.ers.dao.EmployeeDAO;
import com.staed.ers.dao.ReimbursementDAO;

class ReimbursementDAOTest {
	static ReimbursementDAO reimbDAO;
	static EmployeeDAO empDAO;
	
	@BeforeAll
	static void newInstance() {
		reimbDAO = new ReimbursementDAO();
		empDAO = new EmployeeDAO();
	}
	
	@DisplayName("ReimbursementDAO: Get All Reimbursements")
	@Test
	void getAllReimbursementTest() {
		List<Reimbursement> reimbs = reimbDAO.getAllReimbursement();
		assertNotNull(reimbs);
		assertTrue(reimbs.size() > 0);
	}
	
	@DisplayName("ReimbursementDAO: Get Specific Reimbursement")
	@Test
	void getReimbursementTest() {
		Reimbursement reimb = reimbDAO.getReimbursement(1);
		assertNotNull(reimb);
		assertEquals(1, reimb.getId());
	}
	
	@DisplayName("ReimbursementDAO: Employee's Reimbursements")
	@Test
	void employeeReimbursementTest() {
		List<Reimbursement> reimbs = reimbDAO.getEmployeesReimbursements(1);
		assertNotNull(reimbs);
		assertEquals(1, reimbs.get(0).getEmployeeId());
	}
	
	@DisplayName("ReimbursementDAO: Insert and Delete")
	@Test
	void insertAndDeleteTest() {
		Employee emp = empDAO.employeeLogin("mickeyds", "#imlovingit");
		Date rDate = new Date(java.sql.Date.valueOf("2050-01-30").getTime());
		int addRes = reimbDAO.addReimbursement(emp.getId(), emp.getSuperId(), 
				rDate, "It's the future man", 2);
		
		assertTrue(addRes > 0);
		
		int res = reimbDAO._deleteReimbursement(emp.getId(), 2);
		assertTrue(res > 0);
	}
}
