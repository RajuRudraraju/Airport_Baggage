package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
