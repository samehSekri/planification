package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Operateur;

@Repository
public interface OperateurRepository extends JpaRepository<Operateur, String> {

	/**
	 * findOperateurByFirstname
	 * 
	 * @param firstname
	 * @return
	 */
	Operateur findByFirstname(String firstname);

}
