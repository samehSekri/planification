package com.wevioo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.ArticleRepository;
import com.wevioo.dao.OperateurRepository;
import com.wevioo.dao.PolyvalenceRepository;
import com.wevioo.dao.UniteRepository;
import com.wevioo.model.Article;
import com.wevioo.model.NbrePolyvalenceComparator;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DegrePolyvalenceEnum;
import com.wevioo.service.ArticleService;
import com.wevioo.service.OperateurService;
import com.wevioo.service.PolyvalenceService;
import com.wevioo.service.UniteService;
import com.wevioo.utility.excel.ExcelFileUtil;



/**
 * 
 * @author alc
 * 
 */
@Service("PolyvalenceService")

@Transactional(readOnly = true)
public class PolyvalenceServiceImpl implements PolyvalenceService {
	/**
	 *
	 */
	private static final long serialVersionUID = -8741630020370214960L;

	private static final Log LOGGER = LogFactory.getLog(PolyvalenceServiceImpl.class);
	

	@Autowired
	OperateurRepository operateurRepository;
	@Autowired
	ArticleRepository articleRepository; 
	@Autowired
	UniteRepository uniteRepository; 

	@Autowired
	PolyvalenceRepository polyvalenceRepository;


	
	@Autowired
	UniteService uniteService;
	@Autowired
	ArticleService articleService;
	@Autowired
	OperateurService operateurService;

	private List<Polyvalence> getPolyvalenceByArticle(final Row row, FormulaEvaluator evaluator,
			Map<Integer, Operateur> mapMatricules, int sizeList, Unite unite) throws Exception {
		// LOGGER.info("begin getArticleObjectFromCadence (row=" +
		// row.getRowNum()
		// + ")");
		Article article =new Article();
		final Iterator<Cell> cellIterator = row.cellIterator();
		List<Polyvalence> listePolyvalences = new ArrayList<Polyvalence>();
		Polyvalence polyvalence;
		String lastReference = null;
		Cell cell;
		CellValue cellValue = null;
		int i = 0;
		

		while (i < sizeList - 2) {
			if (cellIterator.hasNext()) {
				cell = cellIterator.next();

				i = cell.getColumnIndex();

				// LOGGER.info("Colonne =" + i);
				// Colonne 1 : non utilisee
				if (i == 0) {
					// LOGGER.info("--> valeur cell 1 = " +
					// fileService.readStringFromFile(cell,
					// evaluator, cellValue));
					continue;
				} else if (i == 1) {
					String articleString = ExcelFileUtil.readStringFromFile(cell, evaluator, cellValue);
					if (articleString != null && !articleString.trim().isEmpty()) {
						lastReference = articleString.trim();
						article.setReference(lastReference);
					} else {
//						LOGGER.info(CommonUtil.getMessageResourceString(Constants.ERROR_MESSAGE_REFERENCE_MANQUANTE,
//								new String[] { (row.getRowNum() + 1) + "" }));
						
						return null;
					}
					Article searchedArticle = articleRepository.getOne(lastReference);
					//Unite uniteArticle = null;

					if (searchedArticle == null) {
//						LOGGER.info(CommonUtil.getMessageResourceString(Constants.ERROR_MESSAGE_REFERENCE_NOT_EXIST,
//								new String[] { (row.getRowNum() + 1) + "", lastReference }));
//						
						throw new Exception();//CommonUtil.getMessageResourceString(
//								Constants.ERROR_MESSAGE_REFERENCE_NOT_EXIST,
//								new String[] { (row.getRowNum() + 1) + "", lastReference }));
					}

				} else {
					String value =ExcelFileUtil.readStringFromFile(cell, evaluator, cellValue);

					if (value != null && !value.isEmpty() && !value.trim().equals("")) {
						if (article.getReference() != null && !article.getReference().isEmpty()) {

							if (mapMatricules.get(cell.getColumnIndex()) != null) {

								polyvalence = new Polyvalence();
								polyvalence.setArticle(article);
								Operateur op = mapMatricules.get(cell.getColumnIndex());
								polyvalence.setOperateur(op);
								try {
									DegrePolyvalenceEnum degre = DegrePolyvalenceEnum.valueOf(value);
									polyvalence.setDegre(degre);
								} catch (Exception ex) {
//									throw new Exception(
//											CommonUtil.getMessageResourceString(Constants.ERROR_MESSAGE_DEGRE_ERRONE,
//													new String[] { (op.getMatricule()), article.getReference() }));
								}

//								polyvalence.setDateAffectation(new Date());

								listePolyvalences.add(polyvalence);
							} else {
								// Rien à faire
								LOGGER.info("Colonne vide : " + cell.getColumnIndex());
							}
						} else {
							return null;
						}
					}
				}
			}
		}

		// LOGGER.info("Fin de lecture des articles...");
		return listePolyvalences;
	}




	@Override
	public List<Polyvalence> getPolyvalencesByCriteria(String matriculeOp, String refArticle, Integer firstRow,
			Integer numRows) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Polyvalence> getPolyvalencesQCEByCriteria(String matriculeOp, String refArticle, String unite,
//			Integer firstRow, Integer numRows) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
		
	public Map<String, List<Polyvalence>> getMapArticlePolyvalences(String unite) {
		Unite unite1 = uniteRepository.findOne(unite);

		List<Article> list = articleService.findArticleByUnite( unite1);
		Map<String, List<Polyvalence>> mapArtPoly = new HashMap<String, List<Polyvalence>>();
		
		for(Article article : list){
			mapArtPoly.put(article.getReference(), polyvalenceRepository.findPolyvalenceByArticle( article));
					System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("Ref("+ article.getReference() + ")");
		for(Polyvalence p : mapArtPoly.get(article.getReference())){
			System.out.println("			---> Polyvalence(op:" + p.getOperateur().getMatricule() + ", reste:" + p.getOperateur().getChargeRestante() +")");
		}		System.out.println("++++++++++++++++++++++++++++++++");
		
}
		
		return mapArtPoly;
	}  
	
	public List<Polyvalence> MettreAJourListePolyvalences(List<Operateur> operateurs, List<Polyvalence> polyvalences)  {
		ArrayList<Polyvalence> listPolys = new ArrayList<Polyvalence>();
		for(Polyvalence p : polyvalences){
			for(Operateur op : operateurs){
				if(p.getOperateur().getMatricule().equals(op.getMatricule())){
					p.getOperateur().setNbrePolyvalence(op.getNbrePolyvalence());
					listPolys.add(p);
				}
			}
		}
//		Collections.sort(listPolys, new Comparator<Polyvalence>() {
//			@Override
//			public int compare(final Polyvalence p1,final Polyvalence p2){
//				return p1.getOperateur().getNbrePolyvalence().compareTo(p2.getOperateur().getNbrePolyvalence());
//			}
//		});
//		Arrays.sort(listPolys, Polyvalence.NbrePolyvalenceComparator);
		Collections.sort(listPolys, new NbrePolyvalenceComparator());
			
		return listPolys;
	}
	@Override
	public Map<String, List<Polyvalence>> MettreAJourMapPolyvalences(List<Operateur> operateurs, List<Article> articles, Map<String, List<Polyvalence>> mapArticlePolyvalences) {
		 Map<String, List<Polyvalence>> map = new  HashMap<String, List<Polyvalence>>();
		for(Article a : articles){
			List<Polyvalence> listPolys = new ArrayList<Polyvalence>();
			listPolys = mapArticlePolyvalences.get(a.getReference());
			List<Polyvalence> polyvalences = MettreAJourListePolyvalences(operateurs, listPolys);
			map.put(a.getReference(), polyvalences);
		}
		return map;
	}

	@Override
	public List<Polyvalence> findPolyvalenceByOperateur(Operateur op) {
		if (op != null && !op.equals(null)) {
		
			List<Polyvalence> polyvalences = polyvalenceRepository.findPolyvalenceByOperateur(op);
			if (polyvalences != null) {
				return polyvalences;
			}
		}		return null;
	}

	
	@Override
	public Map<String, List<Polyvalence>> getMapOperateurPolyvalences(String unite) {
		//List<Operateur> list = operateurService.findOperateurByMatriculeAndStatutAndUnite(null, null, unite);
		Unite unite1 = uniteRepository.findOne(unite);
		List<Operateur> list = operateurService.findOperateurByUnite(unite1);
		Map<String, List<Polyvalence>> mapOpPoly = new HashMap<String, List<Polyvalence>>();
		
		for(Operateur op : list){
			mapOpPoly.put(op.getMatricule(), polyvalenceRepository.findPolyvalenceByOperateur(op));
		}
		return mapOpPoly;
	}
	
}
