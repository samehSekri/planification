package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Operateur;
import com.wevioo.model.Unite;

@Repository
public interface OperateurRepository extends JpaRepository<Operateur, String> {

	/**
	 * findOperateurByFirstname
	 * 
	 * @param firstname
	 * @return
	 */
	Operateur findByFirstname(String firstname);
	List<Operateur> findOperateurByUnite(Unite unite);


}
