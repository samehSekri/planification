package com.wevioo.model.json;

import java.io.Serializable;

public class PlanningDetailleJasper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8746675549161229662L;
	
	private String scenario;
	private String matricule;
	private String reference;
	private String firstName;
	private String lastName;
	private Integer quantite;
	private Double tempsGamme;
	private Double tauxOccupation;
	private String editedByFirstName;
	private String editedByLastName;
	private String uniteName;
	private String uniteType;
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Double getTempsGamme() {
		return tempsGamme;
	}

	public void setTempsGamme(Double tempsGamme) {
		this.tempsGamme = tempsGamme;
	}

	public Double getTauxOccupation() {
		return tauxOccupation;
	}

	public void setTauxOccupation(Double tauxOccupation) {
		this.tauxOccupation = tauxOccupation;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getEditedByFirstName() {
		return editedByFirstName;
	}

	public void setEditedByFirstName(String editedByFirstName) {
		this.editedByFirstName = editedByFirstName;
	}

	public String getEditedByLastName() {
		return editedByLastName;
	}

	public void setEditedByLastName(String editedByLastName) {
		this.editedByLastName = editedByLastName;
	}

	public String getUniteName() {
		return uniteName;
	}

	public void setUniteName(String uniteName) {
		this.uniteName = uniteName;
	}

	public String getUniteType() {
		return uniteType;
	}

	public void setUniteType(String uniteType) {
		this.uniteType = uniteType;
	}

}
