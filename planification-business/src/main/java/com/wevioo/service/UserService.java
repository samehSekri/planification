package com.wevioo.service;

import java.util.List;

import com.wevioo.dto.UserDto;
import com.wevioo.model.User;
import com.wevioo.utility.mail.EmailStatus;

public interface UserService {
	/**
	 * Find All users
	 * 
	 * @return List of userDto
	 */
	List<UserDto> findAllUsers();

	/**
	 * Find a user by username
	 * 
	 * @return a userDto
	 */
	UserDto findUserByUsername(String username);

	/**
	 * Find a user by id
	 * 
	 * @return a userDto
	 */
	UserDto findUserById(Long id);

	// /**
	// * Find a user by id
	// * @param id : the User id
	// * @return a User
	// */
	// User findUserByUserId(Long id);

	/**
	 * Find a user by email
	 * 
	 * @return a userDto
	 */
	UserDto findUserByEmail(String email);

	/**
	 * Find a user by email and username
	 * 
	 * @return a userDto
	 */
	User findUserByEmailAndUsername(String email, String username);

	/**
	 * Save and get user
	 * 
	 * @param user
	 *            the user to insert or update
	 * @return User
	 */
	User saveAndGetUser(User user);

	/**
	 * Save and get userDto
	 * 
	 * @param userDto
	 *            the user to insert or update
	 * @return UserDto
	 */
	UserDto saveAndGetUser(UserDto user);

	/**
	 * Enable or Disable user
	 * 
	 * @param userDto
	 *            the userDto to update
	 * @return userDto
	 */
	UserDto changeUserStatus(Long userId);

	/**
	 * Convert a userDto to user
	 */
	User convertUserDtoToUser(UserDto user);

	/**
	 * Convert a user to userDto
	 */
	UserDto convertUserToUserDto(User user);

	/**
	 * Update all informations for specific user and return the updated user
	 * 
	 * @param userDto
	 * @return UserDto
	 */
	UserDto updateUser(UserDto userDto);

	/**
	 * Create a new user
	 * 
	 * @param userDto
	 * @return UserDto
	 */
	UserDto createUser(UserDto userDto, String emailCreationSubject, String httpIpAddress);

	/**
	 * Reset user password
	 * 
	 * @param EmailStatus:
	 *            the email status
	 */
	EmailStatus resetPassword(User user, String emailResetSubject, String httpIpAddress);

}
