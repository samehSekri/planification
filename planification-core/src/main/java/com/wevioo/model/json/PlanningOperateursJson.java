package com.wevioo.model.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wevioo.model.Affectation;
import com.wevioo.model.Operateur;
import com.wevioo.model.Scenario;




public class PlanningOperateursJson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -136674883977228293L;
	
	private Scenario scenario;
	private Operateur operateur;
	private List<Affectation> affectations;
	
	public Scenario getScenario() {
		return scenario;
	}
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
	public Operateur getOperateur() {
		return operateur;
	}
	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}
	public List<Affectation> getAffectations() {
		return affectations;
	}
	public void setAffectations(List<Affectation> affectations) {
		this.affectations = affectations;
	}
	
	
	
}
