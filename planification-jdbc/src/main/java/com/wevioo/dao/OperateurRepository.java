package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Operateur;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutOperateurEnum;

@Repository
public interface OperateurRepository extends JpaRepository<Operateur, String> {

	/**
	 * findOperateurByFirstname
	 * 
	 * @param firstname
	 * @return
	 */
	Operateur findByFirstname(String firstname);
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
	 * @param statut
	 * @param unite
	 * @return
	 */
	List<Operateur> findOperateurByMatriculeAndStatutAndUnite(String matricule,StatutOperateurEnum statut, String unite);
	/*
	 * 
	 * 
	 */
	List<Operateur> findOperateurByStatut(StatutOperateurEnum statut);
		

}
