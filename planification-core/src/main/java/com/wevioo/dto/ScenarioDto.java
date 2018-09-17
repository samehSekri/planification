package com.wevioo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.PeriodeEnum;
import com.wevioo.model.enumeration.StatutScenarioEnum;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "scenario")
@Getter
@Setter
public class ScenarioDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3704600374975094603L;

	
	private Long id;
	
	private String name;
	
	private Date dateCreation;
	
	private Date dateValidation;
	
	private Date dateRejet;
	
	private Date dateDebut;
	
	private Date dateModification;
	
	private double tauxSatisfactionClient;
	
	
	private double tauxSatisfactionProduction;
	
	private double tauxOccupation;
	
	private Unite unite;

	
	private String commentaire;
	
	
	private int numero;
	
	
	private PeriodeEnum periode;

	private StatutScenarioEnum statut;
}