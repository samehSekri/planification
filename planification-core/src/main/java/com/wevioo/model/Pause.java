package com.wevioo.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "pause")
public class Pause implements Serializable {

	private static final long serialVersionUID = -8091798214699484984L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPause;

	@NotNull(message = "{error.pause.label.null}")
	@NotEmpty(message = "{error.pause.label.empty}")
	@Column(length = 50, name = "label")
	@Size(min = 5, max = 50, message = "{error.pause.label.max}")
	private String label;

	@NotNull(message = "{error.pause.heure_debut.null}")
	@NotEmpty(message = "{error.pause.heure_debut.empty}")
	@Column(length = 50, name = "heure_debut")
	private Time heureDebut;

	@NotNull(message = "{error.pause.heure_fin.null}")
	@NotEmpty(message = "{error.pause.heure_fin.empty}")
	@Column(length = 50, name = "heure_fin")

	private Time heureFin;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "horaire_pause", joinColumns = {
			@JoinColumn(name = "id_pause", referencedColumnName = "idPause") }, inverseJoinColumns = {
					@JoinColumn(name = "id_horaire", referencedColumnName = "idHoraire") })
	private List<Horaire> horaires;

	public List<Horaire> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Horaire> horaires) {
		this.horaires = horaires;
	}

	public Long getId_pause() {
		return idPause;
	}

	public void setId_pause(Long id_pause) {
		this.idPause = id_pause;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Time getHeure_debut() {
		return heureDebut;
	}

	public void setHeure_debut(Time heure_debut) {
		this.heureDebut = heure_debut;
	}

	public Time getHeure_fin() {
		return heureFin;
	}

	public void setHeure_fin(Time heure_fin) {
		this.heureFin = heure_fin;
	}

	public Pause(Long id_pause, String label, Time heure_debut, Time heure_fin, List<Horaire> horaires) {
		super();
		this.idPause = id_pause;
		this.label = label;
		this.heureDebut = heure_debut;
		this.heureFin = heure_fin;
		this.horaires = horaires;
	}

	public Pause(Long id_pause) {
		super();
		this.idPause = id_pause;
	}

}
