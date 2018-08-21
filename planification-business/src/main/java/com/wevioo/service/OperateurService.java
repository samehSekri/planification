package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Operateur;
import com.wevioo.model.Unite;

public interface OperateurService {

	/**
	 * Get all Operateurs
	 * 
	 * @return List Operateur
	 */
	List<Operateur> findAllOperateurs();

	/**
	 * 
	 * @param firstname
	 * @return
	 */
	Operateur findOperateurByFirstname(String firstname);
	/**
	 * 
	 * @param unite
	 * @return
	 */
	List<Operateur> findOperateurByUnite(Unite unite);

	/**
	 * 
	 * @param matricule
	 * @return
	 */
	Operateur findOperateurByMatricule(String matricule);

	/**
	 * 
	 * @param email
	 * @param Operateurname
	 * @return
	 */

	Operateur saveAndGetOperateur(Operateur operateur);

	/**
	 * 
	 * @param operateurId
	 * @return
	 */

	// Operateur updateOperateur(Operateur operateur);

	/**
	 * 
	 * @param Operateur
	 * @return
	 */
	Operateur createOperateur(Operateur Operateur);
	/**
	 * 
	 * @param operateur
	 * @return
	 */



	void deleteOperateur(String matricule);
}
