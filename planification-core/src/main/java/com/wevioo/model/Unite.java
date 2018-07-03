package com.wevioo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.model.enumeration.TypeUnite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unite")
public class Unite {

	@Column(name = "name")
	@Id
	private String name;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TypeUnite type;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Unite parent;


	@JsonIgnore
	@OneToMany(mappedBy = "unite", fetch = FetchType.LAZY)
	private List<Operateur> operateurs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "unite", fetch = FetchType.LAZY)
	private List<Article> articles;

	@OneToMany
	@JoinTable(name = "unite_horaire", joinColumns = {
			@JoinColumn(name = "name", referencedColumnName = "name") }, inverseJoinColumns = {
					@JoinColumn(name = "idHoraire", referencedColumnName = "idHoraire") })
	private List<Horaire> horaires;
	

	@OneToMany(mappedBy = "unite", fetch = FetchType.LAZY)
	private List<Scenario> scenarios;



	public Unite(String name, TypeUnite type, Unite parent, List<Operateur> operateurs, List<Article> articles,
			List<Horaire> horaires, List<Scenario> scenarios) {
		super();
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.operateurs = operateurs;
		this.articles = articles;
		this.horaires = horaires;
		this.scenarios = scenarios;
	}

	public Unite() {
		super();
	}


}
