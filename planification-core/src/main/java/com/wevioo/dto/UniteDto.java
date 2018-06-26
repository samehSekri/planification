package com.wevioo.dto;

import java.io.Serializable;

import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter


public class UniteDto implements Serializable {


	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private TypeUnite type;

	private Unite parent;

	public UniteDto(String name, TypeUnite type, Unite parent) {
		super();
		this.name = name;
		this.type = type;
		this.parent = parent;
	}

	public UniteDto() {
		super();
	}

}
