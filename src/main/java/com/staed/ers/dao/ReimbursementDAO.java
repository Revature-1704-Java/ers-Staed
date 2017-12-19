package com.staed.ers.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.staed.ers.beans.Reimbursement;
import com.staed.ers.factory.ReimbursementFactory;

public class ReimbursementDAO extends DAO {
	public List<Reimbursement> getAllReimbursement() {
		String sql = "SELECT * FROM REIMBURSEMENT";
		PreparedStatement ps = prepareStatement(sql);
		List<Reimbursement> res = resultIterator(ps);
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Reimbursement> getEmployeesReimbursements(int id) {
		String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEEID = ?";
		PreparedStatement ps = prepareStatement(sql);
		try {
			ps.setInt(1, id);
		} catch (SQLException e) {
			e.getMessage();
		}
		return resultIterator(ps);
	}

	public Reimbursement getReimbursement(int id) {
		String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENTID = ?";
		PreparedStatement ps = prepareStatement(sql);
		try {
			ps.setInt(1, id);
		} catch (SQLException e) {
			e.getMessage();
		}
		List<Reimbursement> res = resultIterator(ps);
		return res.isEmpty() ? null : res.get(0);
	}

	@SuppressWarnings("unchecked")
	List<Reimbursement> resultIterator(PreparedStatement ps) {
		return super.resultIterator(ps);
	}
	
	@SuppressWarnings("unchecked")
	Reimbursement extractRow(ResultSet rs) {
		try {
			ReimbursementFactory rf = new ReimbursementFactory();

			int id = rs.getInt("REIMBURSEMENTID");
			int eId = rs.getInt("EMPLOYEEID");
			int hId = rs.getInt("HANDLERID");
			Date submission = rs.getDate("SUBMISSIONDATE");
			Date request = rs.getDate("REQUESTDATE");
			String desc = rs.getString("DESCRIPTION");
			int amt = rs.getInt("AMOUNT");

			boolean appro = false;
			if (rs.getInt("APPROVED") > 0)
				appro = true;

			return rf.getReimbursement(id, eId, hId, submission, request, desc, amt, appro);
		} catch (SQLException e) {
			e.getMessage();
			return null;
		}
	}
}
