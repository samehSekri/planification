package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Operateur;

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
}
