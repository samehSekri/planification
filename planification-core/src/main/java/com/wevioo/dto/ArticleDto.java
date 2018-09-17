package com.wevioo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;
import com.wevioo.model.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter 

public class ArticleDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5737345093197940343L;
	/**
	 * 
	 */
	private  String reference ;
	private Integer resteProduire ;
	private Double tempsStandard ;
	private Integer quantite;
	private Date integrationDate;
	private Integer engagementSemaine;
	private String integrationFileName ;
	private User integrationUser;
	private Integer cadence;
	private Double efficience;
	private boolean etat ;
	private List<Polyvalence> polyvalences;
	private Unite unite;
	public ArticleDto(String reference, Integer resteProduire, Double tempsStandard, 
			Integer quantite, Date integrationDate, Integer engagementSemaine, String integrationFileName,
			User integrationUser, Integer cadence, Double efficience, boolean etat, List<Polyvalence> polyvalences,
			Unite unite) {
		super();
		this.reference = reference;
		this.resteProduire = resteProduire;
		this.tempsStandard = tempsStandard;
		
		this.quantite = quantite;
		this.integrationDate = integrationDate;
		this.engagementSemaine = engagementSemaine;
		this.integrationFileName = integrationFileName;
		this.integrationUser = integrationUser;
		this.cadence = cadence;
		this.efficience = efficience;
		this.etat = etat;
		this.polyvalences = polyvalences;
		this.unite = unite;
	}
	public ArticleDto() {
		super();
	}
	
}
