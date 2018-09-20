package com.wevioo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.AffectationRepository;
import com.wevioo.dao.ScenarioRepository;
import com.wevioo.model.Affectation;
import com.wevioo.model.Article;
import com.wevioo.service.AffectationService;



/**
 * 
 * @author alc
 * 
 */
@Service("affectationService")
public class AffectationServiceImpl implements AffectationService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2464371724778860803L;
	@Autowired
	ScenarioRepository scenarioRepository;
	
	
	@Autowired
	AffectationRepository affectationRepository;

	@Transactional
	@Override
	public void saveAffectations(List<Affectation> affectations)
		 {
		affectationRepository.save(affectations);
		
	}

	@Override
	public List<Affectation> findAffectationsByScenarioAndArticle(Long scenarioId, Article article)
			{
		return affectationRepository.findAffectationsByScenarioAndArticle(scenarioId, article);
	}
	
	@Override
	public List<Affectation> findArticlesByScenario(Long scenarioId)  {
		return affectationRepository.findArticlesByScenario(scenarioId);
	}

	@Override
	public List<Affectation> findScenarioByOperateur(String matricule)
			 {
		return affectationRepository.findScenarioByOperateur(matricule);
	}

	@Override
	public List<Affectation> findAffectationsByScenarioAndOperateur(
			Long idScenario, String matricule)  {
		return affectationRepository.findAffectationsByScenarioAndOperateur(idScenario, matricule);
	}
	
	@Override
	public List<Affectation> findAffectationsOperateursByScenario(Long scenarioId)
			 {
		return affectationRepository.findAffectationsOperateursByScenario(scenarioId);
	}

	@Override
	public void delete(List<Affectation> affectations)
			{
		affectationRepository.delete(affectations);
		
	}

	@Override
	public Double calculateSatisfactionClientGlobaleForArticle(Article article)  {
		List<Affectation> list = affectationRepository.getAffectationsByArticleReference(article.getReference());
		double somme = 0.0;
		int nbre= 0;
		for(Affectation aff : list){
			if(aff.getSatisfactionClient()!=null){
				somme = somme + aff.getSatisfactionClient();
				nbre++ ;
			}
		}
		
		if(nbre==0){
			return 0.0;
		}else{
			return somme/nbre;
		}
	}

	@Override
	public List<Affectation> findAffectationsByArticle(Article article)
			 {
		return null;
	}

}
