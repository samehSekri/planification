package com.wevioo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;
@Repository
public interface UniteRepository extends JpaRepository<Unite, String> {

	/**
	 * find unit by name
	 * @param name
	 * @return an unite
	 */
	
	List<Unite> findUniteByType (TypeUnite type);
	/**
	 * 
	 * @param parent
	 * @return
	 */
	List<Unite> findUniteByParent (Unite parent);

	
	//List<Unite> FindUniteByUap(Unite unite);
	
	
	//Unite findUapByUnite(Unite unite) ;
	
	
}
