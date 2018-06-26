package com.wevioo.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.model.JourFerie;

public interface FerieRepository extends JpaRepository<JourFerie, Date> {

}
