package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.beans.Reimbursement;

class ReimbursementTest {
	Reimbursement reimb;
	final Date sDate = new Date(Date.valueOf("2017-12-19").getTime());
	final Date rDate = new Date(Date.valueOf("2018-01-01").getTime());
	
	@BeforeEach
	void newInstance() {
		reimb = new Reimbursement(1, 1, 3, sDate, rDate, "Hey", (float) 5.2, false);
	}

	@DisplayName("Reimbursement: Getters and Setters")
	@Test
	void setHandlerTest() {
		assertEquals(3, reimb.getHandlerId());
		reimb.setHandlerId(4);
		assertEquals(4, reimb.getHandlerId());
		
		Date newDate = new Date(Date.valueOf("2018-05-01").getTime());
		reimb.setRequestDate(newDate);
		assertEquals(newDate, reimb.getRequestDate());
		
		assertEquals("Hey", reimb.getDescription());
		reimb.setDescription("No");
		assertEquals("No", reimb.getDescription());
		
		assertEquals((float) 5.2, reimb.getAmount());
		reimb.setAmount((float) 2.9);
		assertEquals((float) 2.9, reimb.getAmount());
		
		assertFalse(reimb.isApproved());
		reimb.setApproved(true);
		assertTrue(reimb.isApproved());
		
	}

}
