package com.wevioo.dto;

import java.io.Serializable;
import java.util.Date;

import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutScenarioEnum;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FilterScenarioDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3704600374975094603L;

	
	private Date dateCreation;
	
	private Unite unite;

	private String statut;
}