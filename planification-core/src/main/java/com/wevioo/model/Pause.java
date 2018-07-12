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

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
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
	
	private Time heureDebut;

	@NotNull(message = "{error.pause.heure_fin.null}")
	@NotEmpty(message = "{error.pause.heure_fin.empty}")
	

	private Time heureFin;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "horaire_pause", joinColumns = {
			@JoinColumn(name = "id_pause", referencedColumnName = "idPause") }, inverseJoinColumns = {
					@JoinColumn(name = "id_horaire", referencedColumnName = "idHoraire") })
	private List<Horaire> horaires;

	
	public Pause(Long id_pause, String label, Time heure_debut, Time heure_fin, List<Horaire> horaires) {
		super();
		this.idPause = id_pause;
		this.label = label;
		this.heureDebut = heure_debut;
		this.heureFin = heure_fin;
		this.horaires = horaires;
	}

	public Pause() {
		super();
		
	}

}
