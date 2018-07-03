package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
	/**
	 * 
	 * @param article
	 * @return
	 */
	//Article changeArticleStatus(String reference);
	
}
