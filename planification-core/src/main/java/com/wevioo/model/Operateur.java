package com.wevioo.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.model.enumeration.DayOfWeekEnum;
import com.wevioo.model.enumeration.PeriodeEnum;
import com.wevioo.model.enumeration.StatutOperateurEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "operateur")
@NoArgsConstructor

public class Operateur implements Serializable {

	private static final long serialVersionUID = 886569903829221913L;
	@Id
	@NotNull(message = "{error.operateur.matricule.null}")
	@NotEmpty(message = "{error.operateur.matricule.empty}")
	@Column(length = 50, name = "matricule")
	@Size(min = 2, max = 50, message = "{error.operateur.matricule.max}")
	private String matricule;

	@NotNull(message = "{error.operateur.firstname.null}")
	@NotEmpty(message = "{error.operateur.firstname.empty}")
	@Column(length = 50, name = "firstname")
	@Size(min = 2, max = 50, message = "{error.operateur.firstname.max}")
	private String firstname;

	@NotNull(message = "{error.operateur.lastname.null}")
	@NotEmpty(message = "{error.operateur.lastname.empty}")
	@Column(length = 100)
	@Size(min = 2, max = 100, message = "{error.operateur.lastname.max}")
	private String lastname;

	@NotNull(message = "{error.operateur.email.null}")
	@NotEmpty(message = "{error.operateur.email.empty}")
	@Column(length = 50, name = "email")
	@Size(min = 2, max = 50, message = "{error.operateur.firstname.max}")
	private String email;
	@ManyToOne
	@JoinColumn(name = "name_unite", nullable = false)
	private Unite unite;

	@OneToMany(mappedBy = "operateur")
	private List<Conge> conges;

	@JsonIgnore
	@OneToMany(mappedBy = "operateur")
	private List<Polyvalence> polyvalences;
	@Enumerated(value=EnumType.STRING)
	private StatutOperateurEnum statut;

	@Transient
	private Double seuilTolerance;
	
	@Transient
	private Double chargeRestante;
	
	@Transient
	private Integer toleranceMin=80;
	
	@Transient
	private Integer toleranceMax=100;
	
	@Transient
	private boolean checked;
	
	@Transient
	private Integer nbrePolyvalence;
	
	@Transient
	private Boolean affected = false;
	
	@Transient
	private Double chargeHoraireParScenario;
	
	@Transient
	private Double tauxOccupationParScenario;
	
	@Transient
	private Double tauxOccupationMoyen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "operateur", fetch = FetchType.LAZY)
	private List<Affectation> affectations;

	@Override
	public String toString() {
		return "Operateur [matricule=" + matricule + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", unite=" + unite + ", conges=" + conges + ", polyvalences=" + polyvalences + ", checked="
				+ checked + "]";
	}

	public Operateur(String matricule, String firstname, String lastname, String email, Unite unite, List<Conge> conges,
			List<Polyvalence> polyvalences, StatutOperateurEnum statut, Double seuilTolerance, Double chargeRestante,
			Integer toleranceMin, Integer toleranceMax, boolean checked, Integer nbrePolyvalence, Boolean affected,
			Double chargeHoraireParScenario, Double tauxOccupationParScenario, Double tauxOccupationMoyen,
			List<Affectation> affectations) {
		super();
		this.matricule = matricule;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.unite = unite;
		this.conges = conges;
		this.polyvalences = polyvalences;
		this.statut = statut;
		this.seuilTolerance = seuilTolerance;
		this.chargeRestante = chargeRestante;
		this.toleranceMin = toleranceMin;
		this.toleranceMax = toleranceMax;
		this.checked = checked;
		this.nbrePolyvalence = nbrePolyvalence;
		this.affected = affected;
		this.chargeHoraireParScenario = chargeHoraireParScenario;
		this.tauxOccupationParScenario = tauxOccupationParScenario;
		this.tauxOccupationMoyen = tauxOccupationMoyen;
		this.affectations = affectations;
	}

	


	

}
