package com.wevioo.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wevioo.config.AppConfig;
import com.wevioo.config.AppTestConfig;
import com.wevioo.utility.AspectTest;
import com.wevioo.utility.LoggerUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class,  AppTestConfig.class})
public class TestDao {

	/**
	 * Le loggeur LOG4J.
	 */
	private final LoggerUtility logger = new LoggerUtility(this.getClass());

	@Autowired
	private AspectTest aspectTest;
	 
	@Test
	public void defaultTest() {
		logger.trace("TRACE TEST");
		logger.debug("DEBUG TEST");
		logger.info("INFO info");
		logger.warn("WARN TEST");
		logger.error("ERROR TEST");
		logger.fatal("FATAL TEST");
		aspectTest.tests();
	}
	
}
