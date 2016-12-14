package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class InputFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputFileNotFoundException() {
        super();
    }

    public InputFileNotFoundException(String message) {
        super(message);
    }
}
