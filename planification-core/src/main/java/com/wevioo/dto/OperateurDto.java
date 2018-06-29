package com.wevioo.dto;

import java.io.Serializable;
import java.util.List;


import com.wevioo.model.Conge;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OperateurDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricule;
	private String firstname;
	private String lastname;
	private String email;

	private Unite unite;
	private List<Conge> conges;
	private List<Polyvalence> polyvalences;
	
	public OperateurDto(String matricule, String firstname, String lastname, Unite unite,
			List<Conge> conges, List<Polyvalence> polyvalences) {
		super();
		this.matricule = matricule;
		this.firstname = firstname;
		this.lastname = lastname;
		this.unite = unite;
		this.conges = conges;
		this.polyvalences = polyvalences;
	}

	public OperateurDto() {
		super();
	}
	

}
