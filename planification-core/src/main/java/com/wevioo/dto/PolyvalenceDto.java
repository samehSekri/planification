package com.wevioo.dto;

import java.io.Serializable;

import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.PolyvalenceOperateurArticle;
import com.wevioo.model.enumeration.DegrePolyvalenceEnum;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter 

public class PolyvalenceDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1341521644034995003L;

	private PolyvalenceOperateurArticle polyvalenceOperateurArticle;

	private Operateur operateur;
	private Article article;
	private DegrePolyvalenceEnum degre;
	
}
