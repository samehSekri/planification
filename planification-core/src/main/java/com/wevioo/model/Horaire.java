package com.wevioo.model;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wevioo.model.enumeration.DayOfWeekEnum;
import com.wevioo.model.enumeration.PeriodeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "horaire")
public class Horaire {
@Id
@Column(name = "id_horaire", nullable = false, unique = true)
private Long idHoraire;

@Column(name = "jour", nullable = false)
@Enumerated(EnumType.STRING)
private DayOfWeekEnum jour;

@Column(name = "heure_debut", nullable = false)
private Time heureDebut;

@Column(name = "heure_fin", nullable = false)
private Time heureFin;

@ManyToOne(cascade = { CascadeType.ALL })
@JoinColumn(name = "unite_id", nullable = false)
private Unite unite;

@Column(nullable = true)
private PeriodeEnum periode;

@OneToMany(mappedBy = "horaires", fetch = FetchType.EAGER)
private List<Pause> children;



public Horaire() {
	super();
}



public Horaire(Long idHoraire, DayOfWeekEnum jour, Time heureDebut, Time heureFin, Unite unite, PeriodeEnum periode,
		List<Pause> children) {
	super();
	this.idHoraire = idHoraire;
	this.jour = jour;
	this.heureDebut = heureDebut;
	this.heureFin = heureFin;
	this.unite = unite;
	this.periode = periode;
	this.children = children;
}


	

}
