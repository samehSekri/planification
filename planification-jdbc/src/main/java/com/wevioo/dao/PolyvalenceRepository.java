package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;

@Repository
public interface PolyvalenceRepository extends JpaRepository<Polyvalence, Long> {
	/**
	 * 
	 * @param op
	 * @return
	 */
	List<Polyvalence> findPolyvalenceByOperateur(Operateur op);
	/**
	 * 
	 * @param article
	 * @return
	 */
	List<Polyvalence> findPolyvalenceByArticle(Article article);
	/**
	 * 
	 * @param matriculeOp
	 * @param refArticle
	 * @return
	 */
	public List<Polyvalence> findPolyvalencesByOperateurAndArticle(String matriculeOp, String refArticle);
	


}