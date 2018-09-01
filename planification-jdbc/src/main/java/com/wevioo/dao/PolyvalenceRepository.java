package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;

@Repository
public interface PolyvalenceRepository extends JpaRepository<Polyvalence, Long> {
	List<Polyvalence> findPolyvalenceByOperateur(Operateur op);

}