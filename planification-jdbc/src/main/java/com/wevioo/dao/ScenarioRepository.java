package com.wevioo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
	List<Scenario> findScenarioByStatutAndUnite(StatutScenarioEnum statut,Unite unite);
/**
 * 
 * @param dateCreation
 * @return
 */
	List<Scenario> findScenarioByDateCreationAndUnite(Date dateCreation,Unite unite);
	
	// double calculateTauxSatisfactionClient(List<Article> articles);
	
	/**
	 * find scenario by unite
	 * @param name_unite
	 * @return scenario
	 */
	
	
	List<Scenario> findScenarioByUnite(Unite unite);
}
