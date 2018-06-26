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
import com.wevioo.model.Permission;
import com.wevioo.model.enumeration.PermissionEnum;
import com.wevioo.utility.LoggerUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseSpringDataConfig.class })
public class PermissionDaoTest {

	
	/**
	 * Le loggeur LOG4J.
	 */
	private final LoggerUtility logger = new LoggerUtility(this.getClass());

	@Autowired
	private PermissionRepository permissionRepository;
	
	private long id;
	

	@Before
	public void createUser() {
		Permission permission = new Permission();
		permission.setName(PermissionEnum.CREATION_USERS);
		permissionRepository.save(permission);
		assertNotNull(permission.getId());
		id = permission.getId();
	}
	
	@After
	public void deletePermission() {
		permissionRepository.delete(id);
	}

	@Test
	public void findByName() {
		logger.debug("start unit test findByName");
		Permission permission= permissionRepository.findByName(PermissionEnum.CREATION_USERS);
		assertNotNull(permission);
		assertEquals(PermissionEnum.CREATION_USERS, permission.getName());
		logger.debug("success end unit test findByName");
	}
	
}
