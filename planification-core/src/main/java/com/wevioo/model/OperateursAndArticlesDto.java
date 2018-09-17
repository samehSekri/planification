package com.wevioo.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OperateursAndArticlesDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8017658436158460593L;

	/**
	 * Liste des operateurs
	 */
	private List<Operateur> operateurs;
	
	/**
	 * Liste des articles
	 */
	private List<Article> articles;
	
	/**
	 * Selected unite
	 */
	private Unite unite;
	
	/**
	 * Date debut
	 */
	private String dateDebut;

	public OperateursAndArticlesDto(List<Operateur> operateurs, List<Article> articles, Unite unite, String dateDebut) {
		super();
		this.operateurs = operateurs;
		this.articles = articles;
		this.unite = unite;
		this.dateDebut = dateDebut;
	}

	public OperateursAndArticlesDto() {
		super();
	}


	
}
