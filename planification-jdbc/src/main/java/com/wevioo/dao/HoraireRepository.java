package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.model.Horaire;

public interface HoraireRepository extends JpaRepository<Horaire, Long>{
	/**
	 * find horaire by jour
	 * @param jour
	 * @return horaire
	 */
	
//Horaire FindHoraireByJour(String jour);
}
