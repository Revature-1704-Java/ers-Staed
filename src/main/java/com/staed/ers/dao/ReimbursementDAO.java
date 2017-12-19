package com.staed.ers.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
	
	public int addReimbursement(int eId, int hId, Date rDate, String desc, float amt) {
		String sql = "INSERT INTO REIMBURSEMENT(EMPLOYEEID, HANDLERID, "
				+ "REQUESTDATE, DESCRIPTION, AMOUNT) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = prepareStatement(sql);
		int ans = 0;
		try {
			ps.setInt(1, eId);
			ps.setInt(2, hId);
			ps.setDate(3, rDate);
			ps.setString(4, desc);
			ps.setFloat(5, amt);
			ans = ps.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		}
		return ans;
	}
	
	public int _deleteReimbursement(int eId, float amt) {
		String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEEID = ? AND AMOUNT = ?";
		PreparedStatement ps = prepareStatement(sql);
		ResultSet rs = null;
		Reimbursement reimb = null;
		try {
			ps.setInt(1, eId);
			ps.setFloat(2, amt);
			rs = ps.executeQuery();
			while(rs.next()) {
				reimb = extractRow(rs);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		System.out.println(reimb);
		if (reimb == null)
			return 0;
		int rId = reimb.getId();
		
		CallableStatement cs = prepareCall("{? = call DELETE_REIMBURSEMENT (?)}");
		int ans = 0;
		try {
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, rId);
			cs.execute();
			ans = cs.getInt(1);
			
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ans;
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
