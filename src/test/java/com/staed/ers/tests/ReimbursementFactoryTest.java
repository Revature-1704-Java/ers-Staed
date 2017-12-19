package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.beans.Reimbursement;
import com.staed.ers.factory.ReimbursementFactory;

class ReimbursementFactoryTest {
	static ReimbursementFactory rf;

	@BeforeAll
	public static void newInstance() {
		rf = new ReimbursementFactory();
	}

	@DisplayName("ReimbursementFactory: Test for Correct Reimbursement Generation")
	@Test
	void reimbursementFactoryInitTest() {
		String dateString = "2018-05-20";
		Date rDate = new Date(java.sql.Date.valueOf(dateString).getTime());
		java.util.Date utilDate = new java.util.Date();
		Date cDate = new Date(utilDate.getTime());
		Reimbursement reimb = rf.getReimbursement(1, 5, 2, cDate, rDate, "Super Doge", (float) 50.1, false);
		assertEquals(reimb.getId(), 1);
		assertEquals(reimb.getEmployeeId(), 5);
		assertEquals(reimb.getHandlerId(), 2);
		assertEquals(reimb.getSubmissionDate().getTime(), cDate.getTime());
		assertEquals(reimb.getRequestDate().getTime(), rDate.getTime());
		assertEquals(reimb.getDescription(), "Super Doge");
		assertEquals(reimb.getAmount(), (float) 50.10);
		assertFalse(reimb.isApproved());
		
		StringBuilder expSB = new StringBuilder();
		expSB.append("Reimbursement: {id:1, employeeId:5, handlerId:2, submissionDate:");
		expSB.append(cDate + ", requestDate:" + rDate + ", description:");
		expSB.append(reimb.getDescription() + ", amount:50.1, approved:false}");
		assertEquals(reimb.toString(), expSB.toString());
	}

}
