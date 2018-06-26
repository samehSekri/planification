package com.wevioo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wevioo.model.enumeration.DegrePolyvalenceEnum;

@Entity
@Table(name = "polyvalence")
public class Polyvalence implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private PolyvalenceOperateurArticle polyvalenceOperateurArticle;

	private Operateur operateur;
	Article article;
	private DegrePolyvalenceEnum degre;

	public DegrePolyvalenceEnum getDegre() {
		return degre;
	}

	@EmbeddedId
	public PolyvalenceOperateurArticle getPolyvalenceoperateurarticle() {
		return polyvalenceOperateurArticle;
	}

	public void setPolyvalenceoperateurarticle(PolyvalenceOperateurArticle polyvalenceoperateurarticle) {
		this.polyvalenceOperateurArticle = polyvalenceoperateurarticle;
	}

	public void setDegre(DegrePolyvalenceEnum degre) {
		this.degre = degre;
	}

	public Polyvalence() {
		super();
	}

	@ManyToOne
	@JoinColumn(name = "matricule", referencedColumnName = "matricule", insertable = false, updatable = false)

	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	@ManyToOne
	@JoinColumn(name = "reference", referencedColumnName = "reference", updatable = false, insertable = false)

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}