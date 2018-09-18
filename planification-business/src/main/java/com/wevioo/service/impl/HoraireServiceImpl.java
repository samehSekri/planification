package com.wevioo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.wevioo.dao.HoraireRepository;
import com.wevioo.dao.PauseRepository;
import com.wevioo.model.Horaire;
import com.wevioo.model.Pause;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DayOfWeekEnum;
import com.wevioo.model.enumeration.PeriodeEnum;
import com.wevioo.service.HoraireService;

@Service
@Transactional(readOnly = true)
public class HoraireServiceImpl implements HoraireService {

	@Autowired
	private HoraireRepository horaireRepository;
	@Autowired
	private PauseRepository pauseRepository;
	

	/**
	* 
	*/


//	@Override
	public Number getTempsTravail(Unite uap)  {
		List<Horaire> horaires = findHorairesByUnite(uap);
		if(horaires != null){
			//get all pauses
			String idHoraire = horaires.get(0).getId();
			System.out.println("************ id horaire = " + idHoraire);
			List<Pause>pauses = pauseRepository.findPauseByHorairesId(idHoraire);
			if(pauses != null){
				//HeureFin - HeureDebut
				Long duree = horaires.get(0).getHeureFin().getTime() - horaires.get(0).getHeureDebut().getTime();
				for(Pause pause : pauses){
					//HeureFin - HeureDebut - duree des pauses
					 duree -= pause.getHeureFin().getTime() - pause.getHeureDebut().getTime();
				}       
				//Calculate in hours
				return  duree/3600000;
			}
		}
		return null;
	}

	/**
	 * Return l'idParamHoraire formatted (jour_periode_uapName) 
	 */
	public String buildParamHoraireId(DayOfWeekEnum jour, PeriodeEnum periode,
			String uniteName) {
		if(jour != null && ! jour.name().isEmpty() && periode != null && ! periode.name().isEmpty() && uniteName != null && ! uniteName.isEmpty()){
			return jour.name() + "_" + periode.name() + "_" + uniteName;
		}
		return null;
	}
	@Override
	public Horaire findAllHoraires(String id) {
		if (id != null && !id.equals(0)) {
			Horaire horaire = horaireRepository.findOne(id);
			return horaire;
		}
		return null;
	}

	@Transactional
	@Override
	public Horaire createHoraire(Horaire horaire) {

		if (horaire != null) {

			horaire.setId((horaire.getId()));
			horaire.setHeureDebut(horaire.getHeureDebut());
			horaire.setHeureFin(horaire.getHeureFin());
			horaire.setJour(horaire.getJour());

			horaire = horaireRepository.saveAndFlush(horaire);
		}
		return horaire;
	}

	@Override
	public List<Horaire> findAllHoraires() {
		return horaireRepository.findAll();
	}

	@Override
	public List<Horaire> findHorairesByType(Unite uap, PeriodeEnum periode) {
		// TODO Auto-generated method stub
		return null;
	}

		
	
	@Override
	public List<Horaire> findHorairesByUnite(Unite uap)  {
		return horaireRepository.findHoraireByUnite(uap);
	}
	@Override
	public List<Horaire> findHoraireByUnite(Unite uap, DayOfWeekEnum jour) {
		// TODO Auto-generated method stub
		return null;
	}




//	@Override
//	public Number getTempsTravail(Unite uap) throws DatabaseAccessException {
//		return paramHoraireDao.getTempsTravail(uap);
//	}

}
