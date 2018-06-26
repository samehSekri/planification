package com.wevioo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import com.wevioo.model.enumeration.RoleNameEnum;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = -301697364449725379L;

	@Id
	private Long id;

	@NotNull(message = "{error.role.name.null}")
	@NotEmpty(message = "{error.role.name.empty}")
	@Size(max = 50, message = "{error.role.name.max}")
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private RoleNameEnum name;

	// @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	// private List<User> users;
	// @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	// private List<User> users;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private List<Permission> permissions;

	/**
	 * Default constructor
	 */
	public Role() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param permissions
	 */
	public Role(Long id, RoleNameEnum name, List<Permission> permissions) {
		super();
		this.id = id;
		this.name = name;
		this.permissions = permissions;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public RoleNameEnum getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(RoleNameEnum name) {
		this.name = name;
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String getAuthority() {
		return getName().name();
	}

	// public List<User> getUsers() {
	// return users;
	// }
	//
	// public void setUsers(List<User> users) {
	// this.users = users;
	// }

}