package com.wevioo.exception;

public class BatchOperationSaveException extends BatchException {

	private static final long serialVersionUID = 12613259703964718L;

	public BatchOperationSaveException(String operationType, String orderNumber) {
		super("Erreur lors de l'enregistrement de l'opération de type " + operationType
				+ " qui porte le numéro d'ordre " + orderNumber + ".");
	}

	public BatchOperationSaveException(String message) {
		super(message);
	}
}
