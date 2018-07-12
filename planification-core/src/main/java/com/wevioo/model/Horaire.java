package com.wevioo.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "horaire")
public class Horaire implements Serializable {

	private static final long serialVersionUID = -6060829417369736517L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHoraire;

	@NotNull(message = "{error.Horaire.heure_fin.null}")
	@Column( name = "heure_fin")

	private Time heureFin;

	@NotNull(message = "{error.Horaire.heure_debut.null}")
@Column( name = "heure_debut")

	private Time heureDebut;

	@NotNull(message = "{error.Horaire.jour.null}")
	@NotEmpty(message = "{error.Horaire.jour.empty}")
	@Column(length = 50, name = "jour")
	private DayOfWeek jour;

	

	public Horaire() {
		super();
	}



	public Horaire(Long idHoraire, Time heureFin, Time heureDebut, DayOfWeek jour) {
		super();
		this.idHoraire = idHoraire;
		this.heureFin = heureFin;
		this.heureDebut = heureDebut;
		this.jour = jour;
	}

	

}
