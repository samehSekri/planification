	package com.wevioo.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "pause")

public class Pause {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long idPause;
	
	@Column(name = "heure_debut", nullable = false)
	private Time heureDebut;
	
	@Column(name = "heure_fin", nullable = false)
	private Time heureFin;
	
	

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_horaire", nullable = false)
	private Horaire horaires;

	public Pause(Long idPause, Time heureDebut, Time heureFin, Horaire horaires) {
		super();
		this.idPause = idPause;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.horaires = horaires;
	}

	public Pause() {
		super();
	}


}
