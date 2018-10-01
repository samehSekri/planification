package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Article;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;

public interface UniteService {
	
	
	Unite changeEtat(String name);

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


	void deleteUnite(String name);

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

	Unite findUniteByNameIgnoreCase(String name);
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
