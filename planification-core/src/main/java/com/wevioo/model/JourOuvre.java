package com.wevioo.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "jour_ouvre")
public class JourOuvre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	private Date date;
	
	private Time heureDebut;
	
	private Time heureFin;
	
	@ManyToOne
	@JoinColumn(name = "scenario", nullable = false)
	private Scenario scenario;
	
//@OneToMany(mappedBy = "jourOuvre", fetch = FetchType.EAGER)
//private List<Pause> pauses;


}
