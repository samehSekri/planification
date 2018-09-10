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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "article")
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5547091821298834739L;
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

	@Column(name = "quantite")
	private Integer quantite;

	@Column(name = "date_integration", unique = false, nullable = true)
	private Date integrationDate;

	private Integer engagementSemaine;

	@Column(name = "fichier_integration", unique = false, nullable = true)
	private String integrationFileName;

	@ManyToOne
	@JoinColumn(name = "user_integration")
	private User integrationUser;
	@Column(name = "cadence")
	private Integer cadence;

	@Column(name = "efficience")
	private Double efficience;

	@Column(name = "etat")
	private boolean etat;
	@Column(name = "besoin_polyvalence")
	private Double besoinPolyvalence;
	@JsonIgnore
	@OneToMany(mappedBy = "article")
	private List<Polyvalence> polyvalences;

	@ManyToOne
	@JoinColumn(name = "name_unite", nullable = false)
	private Unite unite;

	@Transient
	private boolean checked;

	public Article() {
		super();
	}

	public Article(String reference, Integer resteProduire, Double tempsStandard, Integer quantite,
			Date integrationDate, Integer engagementSemaine, String integrationFileName, User integrationUser,
			Integer cadence, Double efficience, boolean etat, List<Polyvalence> polyvalences, Unite unite,
			boolean checked) {
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
		this.checked = checked;
	}

}
