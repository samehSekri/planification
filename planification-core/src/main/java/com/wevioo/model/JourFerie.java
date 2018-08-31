package com.wevioo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity
@Table(name = "jours_feries")

public class JourFerie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5342750267199654860L;

	@Id
	@NotNull(message = "{error.jours_feries.date.null}")
	@NotEmpty(message = "{error.jours_feries.date.empty}")
	@Column(name = "date_ferie")
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull(message = "{error.jours_feries.label.null}")
	@NotEmpty(message = "{error.jours_feries.label.empty}")
	@Column(length = 50, name = "label")
	@Size(min = 5, max = 50, message = "{error.jours_feries.label.max}")
	private String label;

	

	public JourFerie() {
		super();
	}

	public JourFerie(Date date, String label) {
		super();
		this.date = date;
		this.label = label;
	}


}
