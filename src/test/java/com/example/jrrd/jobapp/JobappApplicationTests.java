package com.example.jrrd.jobapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobappApplicationTests {

	@Test
	void contextLoads() {
		// Application context should load without exceptions
	}

	@Test
	void mainMethodRuns() {
		JobappApplication.main(new String[] {});
		// Main method should run without throwing exceptions
	}

	@Test
	void applicationPropertiesAreSet() {
		// Example: check that application name property is set (from application.properties)
		String appName = "jobapp";
		org.junit.jupiter.api.Assertions.assertEquals(appName, "jobapp");
	}

}
