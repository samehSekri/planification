package com.wevioo.exception;

public class BatchOperationApiConnectionException extends BatchException {

	private static final long serialVersionUID = -2435262525880453240L;

	public BatchOperationApiConnectionException(String apiUrl) {
		super("Echèc de connexion à l'adresse : " + apiUrl);
	}
}
