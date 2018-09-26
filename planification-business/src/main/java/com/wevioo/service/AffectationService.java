package com.wevioo.service;

import java.util.List;

import com.wevioo.model.Affectation;
import com.wevioo.model.Article;
import com.wevioo.model.Scenario;

/**
 *
 * @author alc
 *
 */
public interface AffectationService {

	public void saveAffectations(List<Affectation> affectations);

	public List<Affectation> findAffectationsByScenarioAndArticle(Long scenarioId, Article article);

	public List<Affectation> findAffectationsOperateursByScenario(Long scenarioId);

	public List<Affectation> findScenarioByOperateur(String matricule);

	public List<Affectation> findAffectationsByScenarioAndOperateur(Long idScenario, String matricule);

	public List<Affectation> findArticlesByScenario(Long scenarioId);

	void delete(List<Affectation> affectations);
	
	Affectation saveAndGetScenario(Affectation[] affectations);

	//Double calculateSatisfactionClientGlobaleForArticle(Article article);

	List<Affectation> findAffectationsByArticle(Article article);

	Double calculateSatisfactionClientGlobaleForArticle(Article article);
}
