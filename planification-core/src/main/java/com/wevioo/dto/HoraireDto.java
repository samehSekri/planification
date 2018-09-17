package com.wevioo.dto;

import java.io.Serializable;
import java.sql.Time;

import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DayOfWeekEnum;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter 

public class HoraireDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8571044448232911861L;
	private Long idHoraire;
	private Time heureFin;
	private Time heureDebut;
	private DayOfWeekEnum jour;
	private Unite unite;
	
	public HoraireDto() {
		super();
	}

	public HoraireDto(Long idHoraire, Time heureFin, Time heureDebut, DayOfWeekEnum jour, Unite unite) {
		super();
		this.idHoraire = idHoraire;
		this.heureFin = heureFin;
		this.heureDebut = heureDebut;
		this.jour = jour;
		this.unite = unite;
	}


	
}
