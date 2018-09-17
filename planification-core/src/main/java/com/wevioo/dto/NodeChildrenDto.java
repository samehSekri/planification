package com.wevioo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeChildrenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1412611838483977159L;
	
	private String label;
	private String type;
	private String styleClass;
	private Boolean expanded;
	private NodeChildrenDto[] children; 

}
