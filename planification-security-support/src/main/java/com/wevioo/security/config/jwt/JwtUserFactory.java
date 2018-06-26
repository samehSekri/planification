package com.wevioo.security.config.jwt;

import com.wevioo.dto.UserDto;
import com.wevioo.model.User;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static UserDto create(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPassword(), user.getAuthorities(), user.isEnabled(), user.getLastPasswordResetDate());
	}
}
