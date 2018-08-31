package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Pause;




@Repository
public interface PauseRepository extends JpaRepository<Pause, Long> {



	//List<Pause> findPauseByIdHoraire(Long idhoraire)  ;

	//void deletePausesByIdHoraire(String idParamHoraire) ;
	
	//List<Pause> getAll();

	//void deletePausesByUnite(Unite unite) ;

}
