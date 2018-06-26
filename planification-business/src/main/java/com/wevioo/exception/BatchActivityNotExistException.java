package com.wevioo.exception;

public class BatchActivityNotExistException extends BatchException {

	private static final long serialVersionUID = 1993242634331205510L;

	public BatchActivityNotExistException(String activityId, String warehouseName) {
		super("Erreur, le donneur d'ordre N° " + activityId + " n'existe pas dans l'entrepôt \"" + warehouseName
				+ "\".");
	}

	public BatchActivityNotExistException(String message) {
		super(message);
	}
}
