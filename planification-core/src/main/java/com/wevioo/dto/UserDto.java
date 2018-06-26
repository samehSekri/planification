package com.wevioo.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.model.Permission;
import com.wevioo.model.Role;
import com.wevioo.model.enumeration.RoleNameEnum;

public class UserDto implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8672077352777988171L;

	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;
	private Date lastPasswordResetDate;
	private RoleNameEnum roleName;
	private List<Permission> permissions;

	public UserDto() {
	}

	public UserDto(Long id, String username, String firstname, String lastname, String email, String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		for (GrantedAuthority grantedAuthority : authorities) {
			Role role = ((Role) grantedAuthority);
			this.roleName = role.getName();
			this.permissions = role.getPermissions();
		}
	}

	// @JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	// @JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	// @JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @param lastPasswordResetDate
	 *            the lastPasswordResetDate to set
	 */
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	/**
	 * @return the role
	 */
	public RoleNameEnum getRoleName() {
		return roleName;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRoleName(RoleNameEnum roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
