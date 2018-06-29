package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;

public interface UniteService {

	List<Unite> findAllUnite();

	/**
	 * 
	 * @param Type
	 * @return
	 */
	List<Unite> findUniteByType(TypeUnite type);

	/**
	 * 
	 * @param name
	 * @return
	 */


	
	List<Unite> findUniteByParent(Unite parent);

	/**
	 * 
	 * @param name
	 * @return
	 */
	Unite findUniteByName(String name);

	/**
	 * 
	 */

	//List<Unite>FindUniteByUap(Unite unite);
	/**
	 * 
	 * @param unite
	 * @return
	 */
	Unite saveAndGetUnite(Unite unite);

	// Operateur updateOperateur(Operateur operateur);

	/**
	 * 
	 * @param unite
	 * @return
	 */
	Unite createUnite(Unite unite);
}
