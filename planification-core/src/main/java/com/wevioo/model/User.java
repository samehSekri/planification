package com.wevioo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 8960842438326545113L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "{error.user.username.null}")
	@NotEmpty(message = "{error.user.username.empty}")
	@Column(length = 50, unique = true)
	@Size(min = 5, max = 50, message = "{error.user.username.max}")
	private String username;

	@NotNull(message = "{error.user.password.null}")
	@NotEmpty(message = "{error.user.password.empty}")
	@Column(length = 100)
	@Size(min = 5, max = 100, message = "{error.user.password.max}")
	private String password;

	@NotNull(message = "{error.user.firstname.null}")
	@NotEmpty(message = "{error.user.firstname.empty}")
	@Column(length = 50, name = "firstname")
	@Size(min = 2, max = 50, message = "{error.user.firstname.max}")
	private String firstname;

	@NotNull(message = "{error.user.lastname.null}")
	@NotEmpty(message = "{error.user.lastname.empty}")
	@Column(length = 50, name = "lastname")
	@Size(min = 2, max = 50, message = "{error.user.lastname.max}")
	private String lastname;

	@NotNull(message = "{error.user.email.null}")
	@NotEmpty(message = "{error.user.email.empty}")
	@Column(length = 50, unique = true)
	@Size(min = 4, max = 100, message = "{error.user.email.max}")
	private String email;

	@NotNull(message = "{error.user.enabled.null}")
	private boolean enabled;

	@NotNull(message = "{error.user.lastPasswordResetDate.null}")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_password_reset_date")
	private Date lastPasswordResetDate;

	// @Column(name="profile_picture", nullable= true)
	// private Byte[] profilePicture;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	/**
	 * Default constructor
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param enabled
	 * @param lastPasswordResetDate
	 * @param role
	 */
	public User(Long id, String username, String password, String firstname, String lastname, String email,
			boolean enabled, Date lastPasswordResetDate, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.role = role;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the lastPasswordResetDate
	 */
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	/**
	 * @param lastPasswordResetDate
	 *            the lastPasswordResetDate to set
	 */
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(getRole());
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	// /**
	// * @return the profilePicture
	// */
	// public Byte[] getProfilePicture() {
	// return profilePicture;
	// }
	//
	// /**
	// * @param profilePicture the profilePicture to set
	// */
	// public void setProfilePicture(Byte[] profilePicture) {
	// this.profilePicture = profilePicture;
	// }

}