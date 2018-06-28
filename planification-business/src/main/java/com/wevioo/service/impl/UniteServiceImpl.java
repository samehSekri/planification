package com.wevioo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.UniteRepository;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;
import com.wevioo.service.UniteService;

@Service
@Transactional(readOnly = true)
public class UniteServiceImpl implements UniteService {

	@Autowired
	private UniteRepository uniteRepository;

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
	public List<Unite> findAllUnite() {
		
		return uniteRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	

	/**
	 * {@inheritDoc}
	 */
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Unite saveAndGetUnite(Unite unite) {
		return uniteRepository.saveAndFlush(unite);
	}

	// @Override
	// public Unite updateUnite(Unite Unite) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Transactional
	@Override
	public Unite createUnite(Unite unite) {
		return uniteRepository.saveAndFlush(unite);
		
	}

	
	@Override
	public Unite findUniteByName(String name) {
		
			if (name != null && !name.isEmpty()) {
				Unite unite = uniteRepository.findOne(name);
				if (unite != null) {
					return unite;
				
			}
			
		}
			return null;
			}

	@Override
	public List<Unite> findUniteByType(TypeUnite type) {
		

			List<Unite> unites = uniteRepository.findUniteByType(type);
			
			return unites;
			
		
	}

	@Override
	public List<Unite> findUniteByParent(Unite parent) {
		List<Unite> unites = uniteRepository.findUniteByParent(parent);
		
		return unites;
	}
	

	

	

}
