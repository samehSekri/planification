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
import com.wevioo.model.Role;
import com.wevioo.model.enumeration.RoleNameEnum;
import com.wevioo.utility.LoggerUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseSpringDataConfig.class })
public class RoleDaoTest {

	/**
	 * Le loggeur LOG4J.
	 */
	private final LoggerUtility logger = new LoggerUtility(this.getClass());

	@Autowired
	private RoleRepository roleRepository;
	
	private long id;
	
	
	@Before
	public void createRole() {
		Role role = new Role();
		role.setName(RoleNameEnum.ROLE_ADMIN);
		roleRepository.save(role);
		assertNotNull(role.getId());
		id = role.getId();
	}
	
	@After
	public void deleteUser() {
		roleRepository.delete(id);
	}

	@Test
	public void findByName() {
		logger.debug("<RoleRepository> : start unit test for <findByName> method");
		Role role= roleRepository.findByName(RoleNameEnum.ROLE_ADMIN);
		assertNotNull(role);
		assertEquals(RoleNameEnum.ROLE_ADMIN, role.getName());
		logger.debug("<RoleRepository> : success ebd unit test for <findByName> method");
	}
	
}
