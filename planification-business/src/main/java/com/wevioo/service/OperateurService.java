package com.wevioo.service;

import java.util.List;
import java.util.Map;

import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutOperateurEnum;

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
	 * @param unite
	 * @return
	 */
	List<Operateur> findOperateurByUnite(String unite);

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
	/**$
	 * 
	 * @param operateurs
	 * @param articles
	 * @param mapArtPoly
	 * @return
	 */
	List<Operateur>findOperateurByMatriculeAndStatutAndUnite(String matricule,StatutOperateurEnum statut, String unite);

	List<Operateur> calculateNbrePolyvalencesForOperateurs(List<Operateur> operateurs, List<Article> articles,
			Map<String, List<Polyvalence>> mapArtPoly);
	/**
	 * 
	 * @param object
	 * @param actif
	 * @param object2
	 * @param i
	 * @param j
	 * @return
	 */

	List<Operateur> findOperatuersByCriteria(Object object, StatutOperateurEnum actif, Object object2, int i, int j);
}
