package com.wevioo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.PauseRepository;
import com.wevioo.model.Pause;
import com.wevioo.service.PauseService;

@Service
@Transactional(readOnly = true)
public class PauseServiceImpl implements PauseService {

	@Autowired
	private PauseRepository pauseRepository;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pause> findAllPauses() {
		return pauseRepository.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Pause findPauseByIdPause(Long id) {
		if (id != null && !id.equals(0)) {
			Pause pause = pauseRepository.findOne(id);
			return pause;
		}
		return null;
	}


	


	@Transactional
	@Override
	public Pause createPause(Pause pause) {

		if (pause != null) {

			pause.setIdPause(pause.getIdPause());
			pause.setHeureDebut(pause.getHeureDebut());
			pause.setHeureFin(pause.getHeureFin());
			pause.setHoraires(pause.getHoraires());
			
			pause = pauseRepository.saveAndFlush(pause);
		}
		return pause;	}

	
}
