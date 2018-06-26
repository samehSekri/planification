package com.wevioo.model;
import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="horaire")
public class Horaire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHoraire;

	@NotNull(message = "{error.Horaire.heure_fin.null}")
	@NotEmpty(message = "{error.Horaire.heure_fin.empty}")
	@Column(length = 50, name = "heure_fin")

	private Time heureFin;
	
	@NotNull(message = "{error.Horaire.heure_debut.null}")
	@NotEmpty(message = "{error.Horaire.heure_debut.empty}")
	@Column(length = 50, name = "heure_debut")

	private Time heureDebut;
	
	@NotNull(message = "{error.Horaire.jour.null}")
	@NotEmpty(message = "{error.Horaire.jour.empty}")
	@Column(length = 50, name = "jour")
	@Size(min = 5, max = 50, message = "{error.Horaire.jour.max}")
	private String jour;

	public Long getId_horaire() {
		return idHoraire;
	}

	public void setId_horaire(Long id_horaire) {
		this.idHoraire = id_horaire;
	}

	public Time getHeure_fin() {
		return heureFin;
	}

	public void setHeure_fin(Time heure_fin) {
		this.heureFin = heure_fin;
	}

	public Time getHeure_debut() {
		return heureDebut;
	}

	public void setHeure_debut(Time heure_debut) {
		this.heureDebut = heure_debut;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public Horaire(Long id_horaire, Time heure_fin, Time heure_debut, String jour) {
		super();
		this.idHoraire = id_horaire;
		this.heureFin = heure_fin;
		this.heureDebut = heure_debut;
		this.jour = jour;
	}

	public Horaire(Long id_horaire) {
		super();
		this.idHoraire = id_horaire;
	}
	
}
