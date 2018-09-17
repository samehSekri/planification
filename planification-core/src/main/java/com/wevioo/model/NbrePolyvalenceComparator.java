package com.wevioo.model;

import java.util.Comparator;

public class NbrePolyvalenceComparator implements Comparator<Polyvalence> {

	@Override
	public int compare(Polyvalence o1, Polyvalence o2) {
		return o1.getOperateur().getNbrePolyvalence().compareTo(o2.getOperateur().getNbrePolyvalence());
	}

}
