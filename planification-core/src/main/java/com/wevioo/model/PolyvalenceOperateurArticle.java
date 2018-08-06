package com.wevioo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.ToString;

@Embeddable
@ToString
public class PolyvalenceOperateurArticle  implements Serializable {
	private static final long serialVersionUID = 1L;
	private  String matricule;
	private String reference;
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
	
}
