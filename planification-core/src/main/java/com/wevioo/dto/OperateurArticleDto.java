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
public class OperateurArticleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricule;
	private String firstname;
	private String lastname;
	private String email;
	private boolean checked;
	private Unite unite;
	private List<Conge> conges;
	private List<Polyvalence> polyvalences;

	public OperateurArticleDto() {
		super();
	}

	public OperateurArticleDto(String matricule, String firstname, String lastname, String email, boolean checked,
			Unite unite, List<Conge> conges, List<Polyvalence> polyvalences) {
		super();
		this.matricule = matricule;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.checked = checked;
		this.unite = unite;
		this.conges = conges;
		this.polyvalences = polyvalences;
	}

}
