package com.wevioo.model.enumeration;

public enum DayOfWeekEnum {
	/**
	 * LUNDI
	 */
	LUNDI(2),
	/**
	 * MARDI
	 */
	MARDI(3),
	/**
	 * MERCREDI
	 */
	MERCREDI(4),
	/**
	 * JEUDI
	 */
	JEUDI(5),
	/**
	 * VENDREDI
	 */
	VENDREDI(6),
	/**
	 * SAMEDI
	 */
	SAMEDI(7),
	/**
	 * DIMANCHE
	 */
	DIMANCHE(1);
	
	private int dayOfWeek;

	DayOfWeekEnum(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}
}
