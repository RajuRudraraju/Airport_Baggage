/**
 * This is a domain class for Conveyer System
 */
package com.api.airport.baggagesystem.domain;

/**
 * @author Vijay
 *
 */
public class ConveyorSystem {
	
	private String node1;
	
	private String node2;
	
	private Long travelTime;

	/**
	 * @return the node1
	 */
	public String getNode1() {
		return node1;
	}

	/**
	 * @param node1 the node1 to set
	 */
	public void setNode1(String node1) {
		this.node1 = node1;
	}

	/**
	 * @return the node2
	 */
	public String getNode2() {
		return node2;
	}

	/**
	 * @param node2 the node2 to set
	 */
	public void setNode2(String node2) {
		this.node2 = node2;
	}

	/**
	 * @return the travelTime
	 */
	public Long getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime the travelTime to set
	 */
	public void setTravelTime(Long travelTime) {
		this.travelTime = travelTime;
	}

}
