package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Pause;
@Repository
public interface PauseRepository extends JpaRepository<Pause, Long> {


	

}
