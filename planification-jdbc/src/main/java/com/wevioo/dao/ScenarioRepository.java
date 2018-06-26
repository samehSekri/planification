package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.model.Scenario;

public interface ScenarioRepository extends JpaRepository<Scenario, Long>{
	/**
	 * find scenario by id
	 * @param id
	 * @return scenario
	 */

	//Scenario FindById(Long idScenario);
	/**
	 * find scenario by unite
	 * @param name_unite
	 * @return scenario
	 */
	
	//Scenario FindByUnit(String name_unite);
}
