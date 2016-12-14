package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class BaggageItemDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaggageItemDataException() {
        super();
    }

    public BaggageItemDataException(String message) {
        super(message);
    }
}
