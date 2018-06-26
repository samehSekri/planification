package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	User findByEmail(String email);

	/**
	 * Find user by email and username
	 * @param email
	 * @param username
	 * @return User
	 */
	User findByEmailAndUsername(String email, String username);
}
