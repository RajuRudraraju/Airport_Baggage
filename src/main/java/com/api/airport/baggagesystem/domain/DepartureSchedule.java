/**
 * This is a domain class for departure schedule
 */
package com.api.airport.baggagesystem.domain;

/**
 * @author Vijay
 *
 */
public class DepartureSchedule {
	
	private String flightId;
	
	private String flightGate;
	
	private String destination;
	
	private String flightTime;

	/**
	 * @return the flightId
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * @return the flightGate
	 */
	public String getFlightGate() {
		return flightGate;
	}

	/**
	 * @param flightGate the flightGate to set
	 */
	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the flightTime
	 */
	public String getFlightTime() {
		return flightTime;
	}

	/**
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}
	
	

}
