package com.wevioo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.model.enumeration.PeriodeEnum;
import com.wevioo.model.enumeration.StatutScenarioEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "scenario")

public class Scenario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1212253111841432594L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true)
	private Long id;
	
	private String name;
	
	private Date dateCreation;
	
	private Date dateValidation;
	
	private Date dateRejet;
	
	private Date dateDebut;
	
	private Date dateModification;
	
	private double tauxSatisfactionClient;
	
	@Transient
	private double tauxSatisfactionProduction;
	
	private double tauxOccupation;
	@JsonIgnore
	@ManyToOne
	private Unite unite;
	

	
//	@JsonIgnore
//	@OneToMany(mappedBy = "scenario", fetch = FetchType.LAZY)
//	private List<JourOuvre> jourOuvre;
//	
	@ManyToOne
	@JoinColumn(name = "cree_par")
	private User creePar;
	
	@ManyToOne
	@JoinColumn(name = "modifie_par", nullable = true)
	private User modifiePar;
	
	private String commentaire;
	
	@Transient
	private int numero;
	
	@Column(name = "periode", nullable = true)
	@Enumerated(EnumType.STRING)
	private PeriodeEnum periode;
	
	@Column(name = "statut", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatutScenarioEnum statut;

	public Scenario(Long id, String name, Date dateCreation, Date dateValidation, Date dateRejet, Date dateDebut,
			Date dateModification, double tauxSatisfactionClient, double tauxSatisfactionProduction,
			double tauxOccupation, Unite unite, User creePar, User modifiePar, String commentaire, int numero,
			PeriodeEnum periode, StatutScenarioEnum statut) {
		super();
		this.id = id;
		this.name = name;
		this.dateCreation = dateCreation;
		this.dateValidation = dateValidation;
		this.dateRejet = dateRejet;
		this.dateDebut = dateDebut;
		this.dateModification = dateModification;
		this.tauxSatisfactionClient = tauxSatisfactionClient;
		this.tauxSatisfactionProduction = tauxSatisfactionProduction;
		this.tauxOccupation = tauxOccupation;
		this.unite = unite;
		this.creePar = creePar;
		this.modifiePar = modifiePar;
		this.commentaire = commentaire;
		this.numero = numero;
		this.periode = periode;
		this.statut = statut;
	}

	public Scenario() {
		super();
	}
	

}