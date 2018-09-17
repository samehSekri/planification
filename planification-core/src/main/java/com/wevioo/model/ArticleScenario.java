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
 * @author
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "article_scenario")
public class ArticleScenario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "reference_article", nullable = false)
	private Article article;

	@ManyToOne
	@JoinColumn(name = "id_scenario", nullable = false)
	private Scenario scenario;

	@Column(name = "checked")
	private boolean checked;

	@Column(name = "engagement_semaine")
	private Integer engagementSemaine;

	public ArticleScenario(Long id, Article article, Scenario scenario, boolean checked, Integer engagementSemaine) {
		super();
		this.id = id;
		this.article = article;
		this.scenario = scenario;
		this.checked = checked;
		this.engagementSemaine = engagementSemaine;
	}

	
}
