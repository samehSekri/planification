package com.wevioo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "affectation")
@Getter
@Setter
public class Affectation implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7962130975936159586L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "seuil_min")
	private Integer seuilMin;
	
	@Column(name = "seuil_max")
	private Integer seuilMax;
	
	@Column(name = "engagement_semaine")
	private Integer engagementSemaine;
	
	@Transient
	private Integer ordre;
	
	private Double chargeOperateurTotale;
	
	@Column(name = "charge_horaire")
	private Double chargeHoraire;
	
	@Column(name = "satisfaction_client")
	private Double satisfactionClient;
	
	@Transient
	private Double satisfactionClientGlobale;
	
	@Transient
	private String dateDebut;
	
	@Transient
	private String dateFin;
	
	@Column
	private Boolean realisation;
	
	@Column(name = "quantite", columnDefinition = "integer default 0")
	private Integer quantite;
	
	
	@ManyToOne
	@JoinColumn(name = "operator_id")
	private Operateur operateur;
	
	
	@ManyToOne
	@JoinColumn(name = "ref_article")
	private Article article;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "scenario")
	private Scenario scenario;

	public Affectation(Long id, Integer seuilMin, Integer seuilMax, Integer engagementSemaine, Integer ordre,
			Double chargeOperateurTotale, Double chargeHoraire, Double satisfactionClient,
			Double satisfactionClientGlobale, String dateDebut, String dateFin, Boolean realisation, Integer quantite,
			Operateur operateur, Article article, Scenario scenario) {
		super();
		this.id = id;
		this.seuilMin = seuilMin;
		this.seuilMax = seuilMax;
		this.engagementSemaine = engagementSemaine;
		this.ordre = ordre;
		this.chargeOperateurTotale = chargeOperateurTotale;
		this.chargeHoraire = chargeHoraire;
		this.satisfactionClient = satisfactionClient;
		this.satisfactionClientGlobale = satisfactionClientGlobale;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.realisation = realisation;
		this.quantite = quantite;
		this.operateur = operateur;
		this.article = article;
		this.scenario = scenario;
	}

	public Affectation() {
		super();
	}



	
}
