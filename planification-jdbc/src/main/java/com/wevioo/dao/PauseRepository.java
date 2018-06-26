package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.model.Pause;

public interface PauseRepository extends JpaRepository<Pause, Long> {
	/**
	 * find pause by id
	 * @param Pause
	 * @return pause
	 */
	//Pause FindPauseById (Long id_pause);

	

}
