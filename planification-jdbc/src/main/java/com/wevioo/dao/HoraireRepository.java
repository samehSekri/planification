package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Horaire;
@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long>{
	
	
}
