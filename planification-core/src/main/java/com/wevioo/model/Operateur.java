package com.wevioo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "operateur")
public class Operateur implements Serializable {

	private static final long serialVersionUID = 886569903829221913L;
	@Id
	@NotNull(message = "{error.operateur.matricule.null}")
	@NotEmpty(message = "{error.operateur.matricule.empty}")
	@Column(length = 50, name = "matricule")
	@Size(min = 2, max = 50, message = "{error.operateur.matricule.max}")
	private String matricule;

	@NotNull(message = "{error.operateur.firstname.null}")
	@NotEmpty(message = "{error.operateur.firstname.empty}")
	@Column(length = 50, name = "firstname")
	@Size(min = 2, max = 50, message = "{error.operateur.firstname.max}")
	private String firstname;

	@NotNull(message = "{error.operateur.lastname.null}")
	@NotEmpty(message = "{error.operateur.lastname.empty}")
	@Column(length = 100)
	@Size(min = 2, max = 100, message = "{error.operateur.lastname.max}")
	private String lastname;
	
	@NotNull(message = "{error.operateur.email.null}")
	@NotEmpty(message = "{error.operateur.email.empty}")
	@Column(length = 50, name = "email")
	@Size(min = 2, max = 50, message = "{error.operateur.firstname.max}")
	private String email;
	@ManyToOne
	@JoinColumn(name = "name_unite", nullable = false)
	private Unite unite;

	@OneToMany(mappedBy = "operateur")
	private List<Conge> conges;

	@OneToMany(mappedBy = "operateur")
	private List<Polyvalence> polyvalences;


	public Operateur(String matricule, String firstname, String lastname, Unite unite, List<Conge> conges,
			List<Polyvalence> polyvalences) {
		super();
		this.matricule = matricule;
		this.firstname = firstname;
		this.lastname = lastname;
		this.unite = unite;
		this.conges = conges;
		this.polyvalences = polyvalences;
	}

	

	public Operateur() {
		super();
	}

	

}
