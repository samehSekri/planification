package com.wevioo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import com.wevioo.model.enumeration.PermissionEnum;

@Entity
@Table(name = "PERMISSIONS")
public class Permission implements GrantedAuthority {

	private static final long serialVersionUID = -1447732628480126009L;

	@Id
	private Long id;

	@NotNull(message = "{error.permission.name.null}")
	@NotEmpty(message = "{error.permission.name.empty}")
	@Size(max = 60, message = "{permission.name.role.max}")
	@Column(name = "name", length = 60, unique = true)
	@Enumerated(EnumType.STRING)
	private PermissionEnum name;
	
	/**
	 * Default constructor
	 */
	public Permission() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Permission(Long id, PermissionEnum name/*, String description*/) {
		super();
		this.id = id;
		this.name = name;
		//this.description = description;
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
	public PermissionEnum getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(PermissionEnum name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name.name();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name.name();
	}

}
