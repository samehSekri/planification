package com.wevioo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.model.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {
	/**
	 * find article by reference
	 * 
	 * @param Reference
	 * @return an Article
	 */
	//Article FindArticleByReference(String Reference);
}
