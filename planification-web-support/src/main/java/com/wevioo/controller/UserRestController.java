package com.wevioo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.dto.UserDto;
import com.wevioo.exception.ApiException;
import com.wevioo.service.UserService;
import com.wevioo.utility.MessageUtil;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageUtil messageUtil;

	/**
	 * Find All users
	 * 
	 * @return List of UserDto
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<UserDto> findAllUsers() throws Exception {
		List<UserDto> users = userService.findAllUsers();
		if(users != null) {
			for(UserDto user : users) {
				user.setPassword(null);
			}
		}
		return users;
	}

	/**
	 * Find user By username
	 * 
	 * @return UserDto
	 * @throws Exception
	 */
	@RequestMapping(value = "{username}", method = RequestMethod.GET)
	public @ResponseBody UserDto findUserByUsername(@PathVariable String username) throws Exception {
		return userService.findUserByUsername(username);
	}

	/**
	 * Find user By id
	 * 
	 * @return UserDto
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody UserDto findUserById(@PathVariable Long id) throws Exception {
		return userService.findUserById(id);
	}

	/**
	 * Enable Disable user By id
	 * 
	 * @return UserDto
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public @ResponseBody UserDto changeUserStatus(@PathVariable Long id) throws Exception {
		return userService.changeUserStatus(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object createUser(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
		if (userDto.getId() != null) {
			ApiException error = new ApiException(HttpStatus.CONFLICT, "error.user.id.exist",
					messageUtil.getMessage("error.user.id.exist"));
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		} else if (userService.findUserByUsername(userDto.getUsername().toLowerCase()) != null) {
			ApiException error = new ApiException(HttpStatus.CONFLICT, "error.user.username.exist",
					messageUtil.getMessage("error.user.username.exist"));
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		} else if (userService.findUserByEmail(userDto.getEmail()) != null) {
			ApiException error = new ApiException(HttpStatus.CONFLICT, "error.user.email.exist",
					messageUtil.getMessage("error.user.email.exist"));
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
		String emailCreationSubject = messageUtil.getMessage("email.creation.title");

		userDto = userService.createUser(userDto, emailCreationSubject, request.getLocalAddr());
		return userDto;
	}

	/**
	 * PUT /users : Updates an existing User.
	 *
	 * @param managedUserVM
	 *            the user to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         user,
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Object updateUser(@RequestBody UserDto userDto) {
		UserDto user = userService.findUserByEmail(userDto.getEmail());

		if (user != null && !user.getId().equals(userDto.getId())) {
			ApiException error = new ApiException(HttpStatus.CONFLICT, "error.user.email.exist",
					messageUtil.getMessage("error.user.email.exist"));
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}

		user = userService.findUserByUsername(userDto.getUsername().toLowerCase());
		if (user != null && !user.getId().equals(userDto.getId())) {
			ApiException error = new ApiException(HttpStatus.CONFLICT, "error.user.username.exist",
					messageUtil.getMessage("error.user.username.exist"));
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
		UserDto updatedUser = userService.updateUser(userDto);

		return updatedUser;
	}

}
