package com.wevioo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wevioo.config.DatabaseSpringDataConfig;
import com.wevioo.model.User;
import com.wevioo.utility.LoggerUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseSpringDataConfig.class })
public class UserDaoTest {
	
	/**
	 * Le loggeur LOG4J.
	 */
	private final LoggerUtility logger = new LoggerUtility(this.getClass());

	@Autowired
	private UserRepository userRepository;
	
	private String username = "Test";
	private String eMail = "test@wevioo.com";
	private long id;
	
//	@Before
//	public void createUser() {
//		User user = new User();
//		user.setFirstname("Test");
//		user.setLastname("Unit");
//		user.setEmail(eMail);
//		user.setUsername(username);
//		userRepository.save(user);
//		assertNotNull(user.getId());
//		id = user.getId();
//	}
//	
//	@After
//	public void deleteUser() {
//		userRepository.delete(id);
//	}
//
//	@Test
//	public void findByUsername() {
//		logger.debug("<UserRepository> : start unit test for <findByUsername> method");
//		User user= userRepository.findByUsername(username);
//		assertNotNull(user);
//		assertEquals(eMail, user.getEmail());
//		logger.debug("<UserRepository> : success end unit test for <findByUsername> method");
//	}
//	
//
//	@Test
//	public void findByEmail() {
//		logger.debug("<UserRepository> : start unit test for <findByEmail> method");
//		User user= userRepository.findByEmail(eMail);
//		assertNotNull(user);
//		assertEquals(username, user.getUsername());
//		logger.debug("<UserRepository> : success end unit test for <findByEmail> method");
//	}
	
	@Test
	public void createUser() {
		
	}
	
}
