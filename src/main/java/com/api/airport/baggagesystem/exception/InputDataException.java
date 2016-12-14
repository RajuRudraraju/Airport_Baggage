package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class InputDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputDataException() {
        super();
    }

    public InputDataException(String message) {
        super(message);
    }
}
