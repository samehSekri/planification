package com.wevioo.model.json;

import java.io.Serializable;
import java.util.Date;

import com.wevioo.model.User;



public class FileInfoJson implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 7237632362943426807L;

	private String fileName;
	private Date dateIntegration;
	private User user;

	public Date getDateIntegration() {
		return dateIntegration;
	}

	public void setDateIntegration(Date dateIntegration) {
		this.dateIntegration = dateIntegration;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
