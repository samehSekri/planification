package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Pause;


public interface PauseService {
	

	List<Pause> findAllPauses();
	/**
	 * 
	 * @param id
	 * @return
	 */

	Pause findPauseByIdPause(Long id);
	
/**
 * 
 * @param pause
 * @return
 */
	Pause createPause(Pause pause);
}
