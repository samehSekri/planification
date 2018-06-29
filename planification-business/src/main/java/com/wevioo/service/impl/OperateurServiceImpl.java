package com.wevioo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.OperateurRepository;
import com.wevioo.model.Operateur;
import com.wevioo.service.OperateurService;

@Service
@Transactional(readOnly = true)
public class OperateurServiceImpl implements OperateurService {

	@Autowired
	private OperateurRepository operateurRepository;

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

}
