package com.wevioo.service;

import java.util.List;
import java.util.Map;

import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;

public interface PolyvalenceService {
	
	List<Polyvalence> findPolyvalenceByOperateur(Operateur op);
	public List<Polyvalence> getPolyvalencesByCriteria(String matriculeOp, String refArticle, Integer firstRow,
			Integer numRows);

//	public List<Polyvalence> getPolyvalencesQCEByCriteria(String matriculeOp, String refArticle, String unite,
//			Integer firstRow, Integer numRows);

	public Map<String, List<Polyvalence>> getMapOperateurPolyvalences(String unite) ;

	public Map<String, List<Polyvalence>> getMapArticlePolyvalences(String unite);

	//public void  deletePolyvalencesByUniteName(String uniteName);

	//List<Polyvalence> insertListPolyvalence(List<Polyvalence> list);

	Map<String, List<Polyvalence>> MettreAJourMapPolyvalences(List<Operateur> operateurs, List<Article> articles,
			Map<String, List<Polyvalence>> mapArticlePolyvalences);
}
