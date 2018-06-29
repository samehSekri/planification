package com.wevioo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@NotNull(message = "{error.article.reference.null}")
	@NotEmpty(message = "{error.article.reference.empty}")
	@Column(length = 50, name = "reference")
	@Size(min = 2, max = 50, message = "{error.article.reference.max}")
	private String reference;
	@Column(name = "reste_produire")
	private Integer resteProduire;

	@Column(name = "temps_standard")
	private Double tempsStandard;
	
	private Integer quantiteRestante;

	@NotNull(message = "{error.article.quantite.null}")
	@NotEmpty(message = "{error.article.quantite.empty}")
	@Column(length = 50, name = "quantite")
	private Integer quantite;
	
	@Column(name="date_integration", unique=false, nullable = true)
	private Date integrationDate;
	
	private Integer engagementSemaine;
	
	@Column(name="fichier_integration", unique=false, nullable = true)
	private String integrationFileName;
	
	@ManyToOne
	@JoinColumn(name = "user_integration")
	private User integrationUser;
	@NotNull(message = "{error.article.cadence.null}")
	@NotEmpty(message = "{error.article.cadence.empty}")
	@Column(name = "cadence")
	private Integer cadence;

	@NotNull(message = "{error.article.efficience.null}")
	@NotEmpty(message = "{error.article.efficience.empty}")
	@Column(name = "efficience")
	private Double efficience;

	@NotNull(message = "{error.article.etat.null}")
	@NotEmpty(message = "{error.article.etat.empty}")
	@Column(name = "etat")
	private boolean etat;
	@OneToMany(mappedBy = "operateur")
	private List<Polyvalence> polyvalences;

	@ManyToOne
	@JoinColumn(name = "name_unite", nullable = false)
	private Unite unite;


	public Article() {
		super();
	}


	public Article(String reference, Integer resteProduire, Double tempsStandard, Integer quantiteRestante,
			Integer quantite, Date integrationDate, Integer engagementSemaine, String integrationFileName,
			User integrationUser, Integer cadence, Double efficience, boolean etat, List<Polyvalence> polyvalences,
			Unite unite) {
		super();
		this.reference = reference;
		this.resteProduire = resteProduire;
		this.tempsStandard = tempsStandard;
		this.quantiteRestante = quantiteRestante;
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

	
}
