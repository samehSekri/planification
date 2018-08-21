package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Article;
import com.wevioo.model.Unite;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
	/**
	 * 
	 * @param article
	 * @return
	 
	 */
	List<Article>findArticleByUnite(Unite unite);
	
	//Article changeArticleStatus(String reference);
	
}
