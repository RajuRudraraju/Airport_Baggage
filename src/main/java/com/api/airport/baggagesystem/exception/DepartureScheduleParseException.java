package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class DepartureScheduleParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DepartureScheduleParseException() {
        super();
    }

    public DepartureScheduleParseException(String message) {
        super(message);
    }
}
