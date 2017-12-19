package com.staed.ers.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.staed.ers.ConnectionUtil;
import com.staed.ers.beans.Reimbursement;
import com.staed.ers.factory.ReimbursementFactory;

public class ReimbursementDAO {
	public List<Reimbursement> getAllReimbursement() {
		String sql = "SELECT * FROM REIMBURSEMENT";
		return resultIterator(prepareStatement(sql));
	}

	public Reimbursement getReimbursement(int id) {
		Reimbursement reimb = null;

		String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENTID = ?";
		PreparedStatement ps = prepareStatement(sql);
		try {
			ps.setInt(1, id);
			reimb = resultIterator(ps).get(0);
		} catch (SQLException e) {
			e.getMessage();
		}
		return reimb;
	}

	
	// Helper functions to hide as much implementation as possible in the GET statements
	private List<Reimbursement> resultIterator(PreparedStatement ps) {
		List<Reimbursement> reimbs = new ArrayList<>();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbs.add(extractReimbursement(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return reimbs;
	}

	private PreparedStatement prepareStatement(String sql) {
		Connection conn = ConnectionUtil.getConnection();
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Reimbursement extractReimbursement(ResultSet rs) {
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
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
