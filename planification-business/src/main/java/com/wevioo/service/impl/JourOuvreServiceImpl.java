package com.wevioo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wevioo.dao.JourOuvreRepository;
import com.wevioo.model.JourOuvre;
import com.wevioo.service.JourOuvreService;



/***
 * 
 * @author
 *
 */
@Service("jourOuvreService")
public class JourOuvreServiceImpl implements JourOuvreService, Serializable {
	/**
	 * 
	 */
//	@Autowired
//JourOuvreRepository jourOuvreRepository;
	
	private static final long serialVersionUID = -7011851569187957253L;
	

	@Override
public List<JourOuvre> findJoursOuvresByScenario(Long scenarioId)
			  {
				return null;
//return jourOuvreRepository.findJourOuvreByScenario(scenarioId);
	}

}
