package com.wevioo.utility;

import org.springframework.stereotype.Component;

import com.wevioo.annotation.AspectLogging;

@Component
public class AspectTest {

	@AspectLogging
	public void tests() {
		System.out.println("ssss");
	}
}
