package com.wevioo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.ArticleRepository;
import com.wevioo.model.Article;
import com.wevioo.model.Unite;
import com.wevioo.service.ArticleService;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> findAllArticles() {
		return articleRepository.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Article findArticleByReference(String reference) {
		if (reference != null && !reference.isEmpty()) {
			Article article = articleRepository.findOne(reference);
			return article;
		}
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Article saveAndGetArticle(Article article) {
		return articleRepository.saveAndFlush(article);
	}

	// @Override
	// public Operateur updateOperateur(Operateur operateur) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Transactional
	@Override
	public Article createArticle(Article article) {

		if (article != null) {

			article.setReference(article.getReference());
		
			article.setEfficience(article.getEfficience());

			article.setEtat(true);
			article.setIntegrationDate(article.getIntegrationDate());
			article.setIntegrationFileName(article.getIntegrationFileName());
			article.setResteProduire(article.getResteProduire());
			article.setPolyvalences(article.getPolyvalences());
			article.setIntegrationUser(article.getIntegrationUser());
			article.setEngagementSemaine(article.getEngagementSemaine());
			article.setUnite(article.getUnite());

			article = articleRepository.saveAndFlush(article);
		}
		return article;	}

	@Override
	@Transactional
	public Article changeArticleStatus(String reference) {
		if (reference != null) {
			Article article = articleRepository.findOne(reference);
			if (article != null) {
				if(article.isEtat()==true) {
					
					article.setEtat(false);
					
				}else {
					article.setEtat(true);
				}
				
				article = saveAndGetArticle(article);
				return article;
			}
		}
		return null;
	}

	@Override
	public List<Article> findArticleByUnite(Unite unite) {
			if (unite != null && !unite.equals(null)) {
				List<Article> articles = articleRepository.findArticleByUnite(unite);
				if (articles != null) {
					return articles;
				}
			}
			return null;
	}

	@Override
	public List<Article> findArticlesByReferenceAndUnite(String reference, String unite) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Article> findArticleByOperateur(Operateur op) {
//		if (op != null && !op.equals(null)) {
//			List<Article> articles = articleRepository.findArticleByOperateur(op);
//			if (articles != null) {
//				return articles;
//			}
//		}		return null;
//	}
}
