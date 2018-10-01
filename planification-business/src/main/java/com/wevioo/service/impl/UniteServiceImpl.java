package com.wevioo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.UniteRepository;
import com.wevioo.model.Article;
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
		
		if (unite != null) {

			unite.setName(unite.getName());
			unite.setType(unite.getType());
			unite.setParent(unite.getParent());
			unite = uniteRepository.saveAndFlush(unite);
		}
		return unite;
		
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

	@Override
	public Unite findUniteByNameIgnoreCase(String name) {
		return uniteRepository.findUniteByNameIgnoreCase(name);
	}

//	@Override
//public List<Unite> FindUniteByUap(Unite unite) {
//List<Unite> unites = uniteRepository.FindUniteByUap(unite);
//return unites;
//}
	

	@Override
	@Transactional
	public void deleteUnite(String name) {
		uniteRepository.delete(name);
		

	}

	@Override
	@Transactional
	public Unite changeEtat(String name) {
		if (name != null) {
			Unite unite = uniteRepository.findOne(name);
			if (unite != null) {
//				if(unite.isEtat()==true) {
//					
//					unite.setEtat(false);
//					
//				}else {
//					unite.setEtat(true);
//				}
				unite.setEtat(! unite.isEtat());
				
				unite = saveAndGetUnite(unite);
				return unite;
			}
		}
		return null;
	}

}
