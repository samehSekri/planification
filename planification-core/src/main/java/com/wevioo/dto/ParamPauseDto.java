package com.wevioo.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import com.wevioo.model.Horaire;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DayOfWeekEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ParamPauseDto implements Serializable {

	/**
	 * 
	 */
	
	private Time heureDebut;

	private Time heureFin;

private  DayOfWeekEnum jour; 
private Unite unite;
	

	public ParamPauseDto() {
		super();
	}


	public ParamPauseDto(Time heureDebut, Time heureFin, DayOfWeekEnum jour, Unite unite) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.jour = jour;
		this.unite = unite;
	}



	

}
