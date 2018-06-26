package com.wevioo.mapper;

import org.modelmapper.PropertyMap;

import com.wevioo.dto.OperateurDto;
import com.wevioo.model.Operateur;

public class OperateurMap extends PropertyMap<Operateur, OperateurDto> {
	
	private static OperateurMap operateurMap;
	
	
	
	public static OperateurMap getInstance() {
		if(operateurMap == null) {
			operateurMap = new OperateurMap();
		}
		return operateurMap;
	}
	
	private OperateurMap() {
	}
	@Override
	protected void configure() {
		map().setMatricule(source.getMatricule());
		map().setFirstname(source.getFirstname());
		map().setLastname(source.getLastname());
		map().setConges(source.getConges());
		map().setPolyvalences(source.getPolyvalences());
		map().setUnite(source.getUnite());
	}
}
