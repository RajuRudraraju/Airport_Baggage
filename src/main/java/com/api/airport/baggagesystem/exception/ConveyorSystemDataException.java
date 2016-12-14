package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class ConveyorSystemDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConveyorSystemDataException() {
        super();
    }

    public ConveyorSystemDataException(String message) {
        super(message);
    }
}
