package com.wevioo.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import com.wevioo.model.Horaire;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PauseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8571044448232911861L;
	private Long idPause;

	private String label;

	private Time heureDebut;

	private Time heureFin;

	private List<Horaire> horaires;

	public PauseDto(Long idPause, String label, Time heureDebut, Time heureFin, List<Horaire> horaires) {
		super();
		this.idPause = idPause;
		this.label = label;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.horaires = horaires;
	}

	public PauseDto() {
		super();
	}
	

}
