package com.staed.ers.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.staed.ers.ConnectionUtil;

/**
 * This class contains helper functions to hide as much implementation 
 * as possible from the DAO objects which inherit from it
 *
 */
public abstract class DAO {
	/**
	 * Creates the SQL connection and prepares the SQL query
	 * @param sql - The SQL query desired
	 * @return A Object extending Statement of that SQL query
	 */
	@SuppressWarnings("unchecked")
	private <T> T prepare(String sql, Class<T> type) {
		Connection conn = ConnectionUtil.getConnection();
		try {
			if (type == CallableStatement.class)
				return (T) conn.prepareCall(sql);
			else if (type == PreparedStatement.class);
				return (T) conn.prepareStatement(sql);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Creates the SQL connection and prepares the SQL query
	 * @param sql - The SQL query desired
	 * @return A PreparedStatement of that SQL Query
	 */
	PreparedStatement prepareStatement(String sql) {
		return prepare(sql, PreparedStatement.class);
	}
	
	/**
	 * Creates the SQL connection and prepares the SQL query
	 * @param sql - The SQL query desired
	 * @return A CallableStatement of that SQL Query
	 */
	CallableStatement prepareCallable(String sql) {
		return prepare(sql, CallableStatement.class);
	}
	
	/**
	 * Given an SQL query (the prepared statement), it will
	 * tell the DB to run the query and return an array of the results
	 * @param ps - The PreparedStatement to be run on the SQL DB
	 * @return An array of a specific object type and contains all the results
	 */
	<T> List<T> resultIterator(PreparedStatement ps) {
		List<T> list = new ArrayList<>();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next() ) {
				list.add(extractRow(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return list;
	}
	
	/**
	 *  This is defined to hide implementation from individual query methods
	 * @param rs - ResultSet from a computed SQL query
	 * @return The specific Object Type that the row represents
	 */
	abstract<T> T extractRow(ResultSet rs);
}
