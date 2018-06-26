package com.wevioo.model;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "conge")
public class Conge implements Serializable {

	private static final long serialVersionUID = -8120088004511804740L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conge")
	private Long idConge;

	@NotNull(message = "{error.conge.date_debut.null}")
	@NotEmpty(message = "{error.conge.date_debut.empty}")
	@Column(name = "date_debut")

	private Time dateDebut;

	@NotNull(message = "{error.conge.date_fin.null}")
	@NotEmpty(message = "{error.conge.date_fin.empty}")
	@Column(name = "date_fin")

	private Time dateFin;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "matricule", nullable = false)
	private Operateur operateur;

	public Conge(Long id_conge, Time date_debut, Time date_fin, Operateur operateur) {
		super();
		this.idConge = id_conge;
		this.dateDebut = date_debut;
		this.dateFin = date_fin;
		this.operateur = operateur;
	}

	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	public Long getId_conge() {
		return idConge;
	}

	public void setId_conge(Long id_conge) {
		this.idConge = id_conge;
	}

	public Time getDate_debut() {
		return dateDebut;
	}

	public void setDate_debut(Time date_debut) {
		this.dateDebut = date_debut;
	}

	public Time getDate_fin() {
		return dateFin;
	}

	public void setDate_fin(Time date_fin) {
		this.dateFin = date_fin;
	}

	public Conge() {
		super();

	}

}
