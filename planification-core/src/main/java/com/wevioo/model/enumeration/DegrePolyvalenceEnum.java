package com.wevioo.model.enumeration;

public enum DegrePolyvalenceEnum {
	/**
	 * En formation
	 */
	F,
	/**
	 * Qualifie
	 */
	Q,
	/**
	 * Confirme
	 */
	C,
	/**
	 * Expert
	 */
	E;
	
	public static DegrePolyvalenceEnum degrePolyvalenceEnum(String s){
		if(s.equals(DegrePolyvalenceEnum.F.toString()))
			return F;
		else if(s.equals(DegrePolyvalenceEnum.Q.toString()))
			return Q;
		else if(s.equals(DegrePolyvalenceEnum.C.toString()))
			return C;
		else if(s.equals(DegrePolyvalenceEnum.E.toString()))
			return E;
		else 
			throw new IllegalArgumentException("Degre de polyvalence enum inconnu");
	}
}
