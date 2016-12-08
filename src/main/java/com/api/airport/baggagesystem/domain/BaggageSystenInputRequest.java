/**
 * This class is for baggage input file request
 */
package com.api.airport.baggagesystem.domain;

import java.util.List;

/**
 * @author Vijay
 *
 */
public class BaggageSystenInputRequest {
	
	private List<ConveyorSystem> conveyorSystems;
	
	private List<DepartureSchedule> departureSchedules;
	
	private List<BaggageItems> bagItems;

	/**
	 * @return the conveyorSystems
	 */
	public List<ConveyorSystem> getConveyorSystems() {
		return conveyorSystems;
	}

	/**
	 * @param conveyorSystems the conveyorSystems to set
	 */
	public void setConveyorSystems(List<ConveyorSystem> conveyorSystems) {
		this.conveyorSystems = conveyorSystems;
	}

	/**
	 * @return the departureSchedules
	 */
	public List<DepartureSchedule> getDepartureSchedules() {
		return departureSchedules;
	}

	/**
	 * @param departureSchedules the departureSchedules to set
	 */
	public void setDepartureSchedules(List<DepartureSchedule> departureSchedules) {
		this.departureSchedules = departureSchedules;
	}

	/**
	 * @return the bagItems
	 */
	public List<BaggageItems> getBagItems() {
		return bagItems;
	}

	/**
	 * @param bagItems the bagItems to set
	 */
	public void setBagItems(List<BaggageItems> bagItems) {
		this.bagItems = bagItems;
	}
	

}
