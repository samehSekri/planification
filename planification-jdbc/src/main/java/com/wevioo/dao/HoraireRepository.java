package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Horaire;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DayOfWeekEnum;

@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long>{
	
List<Horaire> findHoraireByUnite(Unite uap,DayOfWeekEnum jour) ;

//	Number getTempsTravail(Unite uap) ;
	
	//String buildParamHoraireId(DayOfWeekEnum jour, PeriodeEnum periode, String uniteName);
}
