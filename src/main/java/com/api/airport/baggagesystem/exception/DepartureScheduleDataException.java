package com.api.airport.baggagesystem.exception;

/**
 * @author Vijay
 */
public class DepartureScheduleDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DepartureScheduleDataException() {
        super();
    }

    public DepartureScheduleDataException(String message) {
        super(message);
    }
}
