package com.wevioo.dto;

import java.io.Serializable;
import java.util.List;


import com.wevioo.model.Conge;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;

public class OperateurDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricule;
	private String firstname;
	private String lastname;
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
	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public OperateurDto() {
		super();
	}


	public String getMatricule() {
		return matricule;
	}
	
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Polyvalence> getPolyvalences() {
		return polyvalences;
	}

	public void setPolyvalences(List<Polyvalence> polyvalences) {
		this.polyvalences = polyvalences;
	}

	public List<Conge> getConges() {
		return conges;
	}

	public void setConges(List<Conge> conges) {
		this.conges = conges;
	}

}
