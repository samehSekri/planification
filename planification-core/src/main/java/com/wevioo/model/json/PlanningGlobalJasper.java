package com.wevioo.model.json;

import java.io.Serializable;

public class PlanningGlobalJasper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1925135394143845683L;
	private String scenario;
	private String matricule;
	private String firstName;
	private String lastName;
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
