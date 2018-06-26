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

	@NotNull(message = "{error.article.quantite.null}")
	@NotEmpty(message = "{error.article.quantite.empty}")
	@Column(length=50, name = "quantite")

	private Integer quantite;

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

	public Article(String reference, Integer quantite, Integer cadence, Double efficience, boolean etat, Date horaire,
			Unite unite) {
		super();
		this.reference = reference;
		this.quantite = quantite;
		this.cadence = cadence;
		this.efficience = efficience;
		this.etat = etat;
		this.unite = unite;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public Article(String reference) {
		super();
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getCadence() {
		return cadence;
	}

	public void setCadence(Integer cadence) {
		this.cadence = cadence;
	}

	public Double getEfficience() {
		return efficience;
	}

	public void setEfficience(Double efficience) {
		this.efficience = efficience;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
	public List<Polyvalence> getPolyvalences() {
		return polyvalences;
	}

	public void setPolyvalences(List<Polyvalence> polyvalences) {
		this.polyvalences = polyvalences;
	}

	

}
