package com.wevioo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wevioo.model.Horaire;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DayOfWeekEnum;
import com.wevioo.model.enumeration.PeriodeEnum;

@Service
public interface HoraireService {
/**
 * 
 * @return
 */
	
	List<Horaire> findAllHoraires();
/**
 * 
 * @param id
 * @return
 */
//	Number findTempsTravail(Unite uap);
	/**
	 * 
	 * @param uap
	 * @param periode
	 * @return
	 */

	List<Horaire> findHorairesByType(Unite uap, PeriodeEnum periode);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Horaire findAllHoraires(String id);
	/**
	 * 
	 * @param uap
	 * @return
	 */
	
List<Horaire> findHoraireByUnite(Unite uap,DayOfWeekEnum jour);
	/**
	 * 
	 * @param horaire
	 * @return
	 */


	Horaire createHoraire(Horaire horaire);
	/**
	 * 
	 * @param uap
	 * @return
	 */
	Number getTempsTravail(Unite uap) ;

	//List<Horaire> getParamHorairesByType(Unite uap, PeriodeEnum periode)	;

	List<Horaire> findHorairesByUnite(Unite uap);
}
