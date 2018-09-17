package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Unite;


public interface ArticleService {
	/**findArticleByUnite
	 * 
	 * @param unite
	 * @return
	 */
	List<Article> findArticleByUnite(Unite unite);
	/**
	 * 
	 * @param op
	 * @return
	 */
	//List<Article> findArticleByOperateur(Operateur op);
/**
 * 
 * @return
 */
	
	
	List<Article> findAllArticles();

	/**
	 * 
	 * @param firstname
	 * @return
	 */
	Article findArticleByReference(String reference);
	/**
	 * 
	 * @param artcile
	 * @return
	 */

	Article changeArticleStatus(String reference);
	/**
	 * 
	 * @param article
	 * @return
	 */
	Article saveAndGetArticle(Article article);


	// Operateur updateOperateur(Operateur operateur);

	/**
	 * 
	 * @param article
	 * @return
	 */
	Article createArticle(Article article);
	/**
	 * 
	 * @param reference
	 * @param unite
	 * @return
	 */
     List<Article> findArticlesByReferenceAndUnite(String reference,String unite) ;
         
}
