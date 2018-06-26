package com.wevioo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "scenario")
public class Scenario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_scenario")
	private Long idScenario;

	@NotNull(message = "{error.scenario.qualification.null}")
	@NotEmpty(message = "{error.scenario.qualification.empty}")
	@Column(length = 50, name = "qualification")
	@Size(min = 5, max = 50, message = "{error.scenario.qualification.max}")
	private String qualification;

	@NotNull(message = "{error.scenario.date_depart.null}")
	@NotEmpty(message = "{error.scenario.date_depart.empty}")
	@Column(name = "date_depart")
	@Temporal(TemporalType.DATE)
	private Date dateDepart;

	@NotNull(message = "{error.scenario.taux_occupation.null}")
	@NotEmpty(message = "{error.scenario.taux_occupation.empty}")
	@Column(length = 50, name = "taux_occupation")
	@Size(min = 5, max = 50, message = "{error.scenario.taux_occupation.max}")
	private String tauxOccupation;

	@NotNull(message = "{error.scenario.seuil_tolerence.null}")
	@NotEmpty(message = "{error.scenario.seuil_tolerence.empty}")
	@Column(length = 50, name = "seuil_tolerence")
	@Size(min = 5, max = 50, message = "{error.scenario.seuil_tolerence.max}")
	private Integer seuilTolerence;

	@ManyToOne
	@JoinColumn(name = "name_unite", nullable = false)
	private Unite unite;

	// @OneToMany(mappedBy = "date", fetch = FetchType.LAZY)
	// private List<JourFerie> jourFeries;

	public Scenario() {
		super();

	}

	public Scenario(Long idScenario, String qualification, Date dateDepart, String tauxOccupation,
			Integer seuilTolerence, Unite unite) {
		super();
		this.idScenario = idScenario;
		this.qualification = qualification;
		this.dateDepart = dateDepart;
		this.tauxOccupation = tauxOccupation;
		this.seuilTolerence = seuilTolerence;
		this.unite = unite;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public Long getId_scenario() {
		return idScenario;
	}

	public void setId_scenario(Long id_scenario) {
		this.idScenario = id_scenario;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Date getDate_depart() {
		return dateDepart;
	}

	public void setDate_depart(Date date_depart) {
		this.dateDepart = date_depart;
	}

	public String getTaux_occupation() {
		return tauxOccupation;
	}

	public void setTaux_occupation(String taux_occupation) {
		this.tauxOccupation = taux_occupation;
	}

	public Integer getSeuil_tolerence() {
		return seuilTolerence;
	}

	public void setSeuil_tolerence(Integer seuil_tolerence) {
		this.seuilTolerence = seuil_tolerence;
	}

	public Long getIdScenario() {
		return idScenario;
	}

	public void setIdScenario(Long idScenario) {
		this.idScenario = idScenario;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public String getTauxOccupation() {
		return tauxOccupation;
	}

	public void setTauxOccupation(String tauxOccupation) {
		this.tauxOccupation = tauxOccupation;
	}

	public Integer getSeuilTolerence() {
		return seuilTolerence;
	}

	public void setSeuilTolerence(Integer seuilTolerence) {
		this.seuilTolerence = seuilTolerence;
	}
	//
	// public List<JourFerie> getJourFeries() {
	// return jourFeries;
	// }
	//
	// public void setJourFeries(List<JourFerie> jourFeries) {
	// this.jourFeries = jourFeries;
	// }

}
