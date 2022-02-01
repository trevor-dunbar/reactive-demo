package com.reactivepractice.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DemoApplicationTests {

	@Test
	void restTemplateReturnsHello() {
	ImperativeExample imperativeExample = new ImperativeExample();

		String response = imperativeExample.callExternalService_blocking();


	}

}
