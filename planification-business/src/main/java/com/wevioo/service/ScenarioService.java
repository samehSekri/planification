package com.wevioo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wevioo.model.Affectation;
import com.wevioo.model.Article;
import com.wevioo.model.JourOuvre;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Scenario;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutScenarioEnum;
import com.wevioo.utility.DateUtils;



public interface ScenarioService {
	/**
	 * 
	 * @param id
	 * @return
	 */

	Scenario findScenarioById(Long id);

	/**
	 * 
	 * @return
	 */

	List<Scenario> findAllScenario();
	/**
	 * 
	 * @return
	 */
	List<Scenario> findScenarioByUnite(Unite unite);
	/**
	 * 
	 * @param statut
	 * @return
	 */
	List<Scenario> findScenarioByStatut(StatutScenarioEnum statut);/**
 * 
 * @param dateCreation
 * @return
 */
	List<Scenario> findScenarioByDateCreation(Date dateCreation);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Scenario validerScenario(Long id);
	/**
	 * 
	 * @param id
	 */

	void delete(Long id);
	/**
	 * 
	 * @param id
	 * @return
	 */

	Scenario rejeterScenario(Long id);
	/**
	 * 
	 * @param idScenario
	 * @return
	 */

	Scenario getUapByScenario(Long idScenario);
	/**
	 * 
	 * @param scenario
	 * @return
	 */
	Scenario saveAndGetScenario(Scenario scenario);
	
	/**
	 * 
	 * @param articles
	 * @return
	 */

	double calculateTauxSatisfactionProduction( List<Article> articles);
	/**
	 * 
	 * @param operateurs
	 * @param tempsOuverture
	 * @return
	 */

//	double calculateTauxOccupation(List<Operateur> operateurs, double tempsOuverture);
	/**
	 * 
	 * @param scenario
	 * @param operateur
	 * @return
	 */
	public double calculateTauxOccupation(List<Affectation> affectations,
			List<Operateur> operateurs, double tempsOuverture) ;
	double calculateTauxOccupationParOperateur(Scenario scenario, Operateur operateur);
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<JourOuvre> findJourOuvreByUniteAndDate(Unite unite,Date dateDebut) ;

	List<JourOuvre> findJourOuvreByScenario(Long scenarioId);

	double calculateTauxSatisfactionClient(List<Affectation> affectations,
			List<Article> articles);

	List<Affectation> lancerAlgorithme(List<Operateur> operateurs,
			List<Article> articles,
			Map<String, List<Polyvalence>> mapOperateursPolyvalences,
			Map<String, List<Polyvalence>> mapArticlesPolyvalences,
			List<List<Affectation>> result, int indiceArticle,
			double tempsTravaille, List<Operateur> allOperators)
			;
}
