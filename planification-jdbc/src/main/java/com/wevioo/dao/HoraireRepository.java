package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Horaire;
import com.wevioo.model.Pause;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DayOfWeekEnum;
import com.wevioo.model.enumeration.PeriodeEnum;


@Repository
public interface HoraireRepository extends JpaRepository<Horaire, String>{
	
List<Horaire> findHoraireByUnite(Unite uap) ;

//List<Pause> findPauseByIdHoraire(Long horaire)  ;


//String buildParamHoraireId(DayOfWeekEnum jour, PeriodeEnum periode, String uniteName);
}
