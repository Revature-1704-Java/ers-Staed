package com.staed.ers.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.staed.ers.App;

class AppTest {
	App app;

	@DisplayName("App: Correct Initialization")
	@Test
	void appInitTest() {
		app = new App();
		assertNotNull(app.getService());
		
		assertNotNull(app.welcomeText());
	}
}
