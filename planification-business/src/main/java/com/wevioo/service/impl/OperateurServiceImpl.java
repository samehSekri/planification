package com.wevioo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.OperateurRepository;
import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutOperateurEnum;
import com.wevioo.service.OperateurService;

@Service
@Transactional(readOnly = true)
public class OperateurServiceImpl implements OperateurService {

	@Autowired
	private OperateurRepository operateurRepository;
	private String entityName = "Operateur";

	// @Autowired
	// private CongeRepository congeRepository;
	//
	// @Autowired
	// private PolyvalenceRepository polyvalenceRepository;
	//
	// @Autowired
	// private UniteRepository uniteRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Operateur> findAllOperateurs() {
		return operateurRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operateur findOperateurByFirstname(String firstname) {
		if (firstname != null && !firstname.isEmpty()) {
			Operateur operateur = operateurRepository.findByFirstname(firstname);
			return operateur;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operateur findOperateurByMatricule(String matricule) {
		if (matricule != null && !matricule.isEmpty()) {
			Operateur operateur = operateurRepository.findOne(matricule);
			if (operateur != null) {
				return operateur;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Operateur saveAndGetOperateur(Operateur operateur) {
		return operateurRepository.saveAndFlush(operateur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteOperateur(String matricule) {
		operateurRepository.delete(matricule);
		
	}

	// @Override
	// public Operateur updateOperateur(Operateur operateur) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Transactional
	@Override
	public Operateur createOperateur(Operateur operateur) {

		if (operateur != null) {

			operateur.setMatricule(operateur.getMatricule());
			operateur.setFirstname(operateur.getFirstname());
			operateur.setLastname(operateur.getLastname());
			operateur.setEmail(operateur.getEmail());

			operateur.setUnite(operateur.getUnite());

			operateur = operateurRepository.saveAndFlush(operateur);
		}
		return operateur;
	}

	@Override
	public List<Operateur> findOperateurByUnite(Unite unite) {
		if (unite != null && !unite.equals(null)) {
			List<Operateur> operateurs = operateurRepository.findOperateurByUnite(unite);
			if (operateurs != null) {
				return operateurs;
			}
		}
		return null;
	}

	@Override
	public List<Operateur> findOperateurByUnite(String unite) {
		if (unite != null) {
			List<Operateur> operateurs = operateurRepository.findOperateurByUnite(unite);
			if (operateurs != null) {
				return operateurs;
			}
		}
		return null;
	}

	@Override
	public List<Operateur> calculateNbrePolyvalencesForOperateurs(List<Operateur> operateurs, List<Article> articles,
			Map<String, List<Polyvalence>> mapArticlePolyvalences) {
		for (Operateur op : operateurs) {
			op.setNbrePolyvalence(0);
			for (Article article : articles) {
				List<Polyvalence> listPolyvalences = new ArrayList<Polyvalence>();
				listPolyvalences = mapArticlePolyvalences.get(article.getReference());
				for (Polyvalence p : listPolyvalences) {
					if (op.getMatricule().equals(p.getOperateur().getMatricule())) {
						op.setNbrePolyvalence(op.getNbrePolyvalence() + 1);
					}
				}
			}
		}
		return operateurs;
	}

	@Override
	public List<Operateur> findOperateurByMatriculeAndStatutAndUnite(String matricule, StatutOperateurEnum statut,
			String unite) {

		List<Object> param = new ArrayList<Object>();

		StringBuffer queryStr = new StringBuffer("SELECT u FROM " + this.entityName + " u");
		queryStr.append(" WHERE 1=1");

		if (matricule != null && !matricule.isEmpty()) {
			queryStr.append(" AND u.matricule = ?");
			param.add(matricule);
		}

		if (statut != null && !statut.name().isEmpty()) {
			queryStr.append(" AND u.statut = ?");
			param.add(statut);
		}

		if (unite != null && !unite.isEmpty()) {
			queryStr.append(" AND (u.unite.parent.parent.name = ?");
			queryStr.append(" OR u.unite.parent.name = ?");
			queryStr.append(" OR u.unite.name = ?)");
			param.add(unite);
			param.add(unite);
			param.add(unite);
		}

		// List<Operateur> list =
		// executeResultListIgnoreNullValuesByRang(queryStr.toString(),
		// firstRow, numRows, param.toArray());
		// return list;
		return null;
	}

	@Override
	public List<Operateur> findOperatuersByCriteria(Object object, StatutOperateurEnum actif, Object object2, int i,
			int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operateur> findOperatuersByStatut(StatutOperateurEnum statut) {
		if (statut != null ) {
			List<Operateur> operateurs = operateurRepository.findOperateurByStatut(statut);
			if (operateurs != null) {
				return operateurs;
			}	
			
	}
		return null;}

}
