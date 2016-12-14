package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class InputFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputFileException() {
        super();
    }

    public InputFileException(String message) {
        super(message);
    }
}
