package com.staed.ers.factory;

import java.sql.Date;

import com.staed.ers.beans.Reimbursement;

public class ReimbursementFactory {
	public Reimbursement getReimbursement(int id, int eId, int hId,
			Date sDate, Date rDate, String desc, int amt, boolean appro ) {
		return new Reimbursement(id, eId, hId, sDate, rDate, desc, amt, appro);
	}
}
