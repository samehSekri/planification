package com.wevioo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author wat
 *
 */
@Getter
@Setter

@Entity
@Table(name = "operateur_scenario")
public class OperateurScenario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="matricule_operateur", nullable = false)
	private Operateur operateur;
	
	@ManyToOne
	@JoinColumn(name="id_scenario", nullable = false)
	private Scenario scenario;
	
	

	@Column(name = "checked")
	private boolean checked;
	
	@Column(name = "tolerance_min")
	private Integer toleranceMin;
	
	@Column(name = "tolerance_max")
	private Integer toleranceMax;
	
	@Column(name = "seuilTolerance")
	private Double seuilTolerance;
	
	
}
