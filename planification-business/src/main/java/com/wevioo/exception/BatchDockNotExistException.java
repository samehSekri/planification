package com.wevioo.exception;

public class BatchDockNotExistException extends BatchException {
	private static final long serialVersionUID = -6079669719677190154L;

	public BatchDockNotExistException(String dockNumber, String warehouseName) {
		super("Erreur, le quai N° " + dockNumber + " n'existe pas dans l'entrepôt \"" + warehouseName + "\".");
	}

	public BatchDockNotExistException(String message) {
		super(message);
	}
}
