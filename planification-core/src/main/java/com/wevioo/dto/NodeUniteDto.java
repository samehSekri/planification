package com.wevioo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeUniteDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1811870114473681590L;
	
	private String label;
	private NodeDataDto data; 
	private String icon;
	private Object expandedIcon;
	private Object collapsedIcon;
	private NodeUniteDto[] children;
	private Boolean leaf;
	private Boolean expanded;
	private String type;
	private NodeUniteDto parent;
	private Boolean partialSelected;
	private String styleClass;
	private Boolean draggable;
	private Boolean selectable;
	
}
