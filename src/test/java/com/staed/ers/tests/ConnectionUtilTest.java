package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.ConnectionUtil;

class ConnectionUtilTest {
	@DisplayName("ConnectionUtil: Returns proper connection")
	@Test
	void getConnectionTest() {
		assertNotEquals(null, ConnectionUtil.getConnection());
	}
	
	@DisplayName("ConnectionUtil: Throws SQLException on invalid login")
	@Test
	void invalidLoginTest() {
		Assertions.assertThrows(SQLException.class, () -> {
			InputStream in = null;
			try {
				in = new FileInputStream("src/test/resources/connection.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			ConnectionUtil._getConnection(in);
		});
	}

}
