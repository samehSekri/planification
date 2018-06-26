package com.wevioo.service;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wevioo.config.ServiceConfig;
import com.wevioo.dto.UserDto;
import com.wevioo.service.config.TestConfig;
import com.wevioo.utility.LoggerUtility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class, TestConfig.class })
public class UserServiceTest {

	@Autowired
	private UserService userService;

	// @Autowired
	// private EmailHtmlSender emailHtmlSender;

	LoggerUtility log = new LoggerUtility(this.getClass());

	public void test() {
		findAllUsers();
	}

	public void findAllUsers() {
		List<UserDto> users = userService.findAllUsers();
		log.debug("***********************");
		for (UserDto user : users) {
			log.debug(user.toString());

		}
	}

	public void findUserByName(String username) {
		UserDto user = userService.findUserByUsername(username);
		log.info(user.toString());
	}

	public void findUserById(Long id) {
		UserDto user = userService.findUserById(id);
		log.info(user.toString());
	}
}
