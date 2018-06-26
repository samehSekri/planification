package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.model.Conge;

public interface CongeRepository extends JpaRepository<Conge, Long> {
/**
 * find conge by id
 * @param id
 * @return conge
 */
	
	//Conge FindCongeById(Long idConge);
}
