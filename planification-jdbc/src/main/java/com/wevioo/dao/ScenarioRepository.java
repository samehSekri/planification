package com.wevioo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.JourOuvre;
import com.wevioo.model.Scenario;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutScenarioEnum;
@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long>{
	
	/**
	 * 
	 * @param statut
	 * @return
	 */
	List<Scenario> findScenarioByStatut(StatutScenarioEnum statut);
/**
 * 
 * @param dateCreation
 * @return
 */
	List<Scenario> findScenarioByDateCreation(Date dateCreation);
	
	// double calculateTauxSatisfactionClient(List<Article> articles);
	
	/**
	 * find scenario by unite
	 * @param name_unite
	 * @return scenario
	 */
	
	
	List<Scenario> findScenarioByUnite(Unite unite);
	/**
	 * 
	 * @param unite
	 * @param dateDebut
	 * @return
	 */
	 List<JourOuvre> findJourOuvreByUniteAndDateDebut(Unite unite, Date dateDebut) ;
}
