package com.wevioo.mapper;

import org.modelmapper.PropertyMap;

import com.wevioo.dto.UserDto;
import com.wevioo.model.User;

public class UserMap extends PropertyMap<User, UserDto> {
	
	private static UserMap userMap;
	
	
	
	public static UserMap getInstance() {
		if(userMap == null) {
			userMap = new UserMap();
		}
		return userMap;
	}
	
	private UserMap() {
	}
	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setAuthorities(source.getAuthorities());
		map().setEmail(source.getEmail());
		map().setEnabled(source.isEnabled());
		map().setFirstname(source.getFirstname());
		map().setLastname(source.getLastname());
		map().setUsername(source.getUsername());
		//map().setDock(source.getDock());
		map().setLastPasswordResetDate(source.getLastPasswordResetDate());
		map().setPassword(source.getPassword());
		//map().setRoleName(source.getRoleName());
	}
}
