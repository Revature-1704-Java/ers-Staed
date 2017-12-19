package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.Service;
import com.staed.ers.dao.EmployeeDAO;
import com.staed.ers.dao.ReimbursementDAO;

class ServiceTest {
	Service service;
	ReimbursementDAO reimb;
	
	@BeforeEach
	void newinstance() {
		reimb = new ReimbursementDAO();
		service = new Service(new EmployeeDAO(), reimb);
	}
	
	@DisplayName("Service: Correct Initialization")
	@Test
	void serviceInitTest() {
		assertNull(service.getUser());
		assertFalse(service.loggedIn());
	}
	
	@DisplayName("Service: Fail login") 
	@Test
	void serviceBadLoginTest() {
		assertFalse(service.login("doge2017", "#notmypassword"));
		assertNull(service.getUser());
	}
	
	@DisplayName("Service: Log in")
	@Test
	void serviceLoginTest() {
		assertTrue(service.login("doge2018", "#notmydog"));
		assertNotNull(service.getUser());
	}
	
	@DisplayName("Service: Log out")
	@Test
	void serviceLogoutTest() {
		assertFalse(service.logout());
		
		service.login("doge2018", "#notmydog");
		assertNotNull(service.getUser());
		
		assertTrue(service.logout());
		assertNull(service.getUser());
		
	}
	
	@DisplayName("Service: Viewall")
	@Test
	void serviceViewallTest() {
		assertFalse(service.viewAll());
		service.login("doge2018", "#notmydog");
		assertTrue(service.viewAll());
	}
}
