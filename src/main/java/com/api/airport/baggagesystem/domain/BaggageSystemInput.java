package com.api.airport.baggagesystem.domain;

import java.util.Set;

/**
 * @author Vijay
 * This class is for baggage input file request
 */
public class BaggageSystemInput {
	
	private Set<ConveyorSystem> conveyorSystems;
	
	private Set<DepartureSchedule> departureSchedules;
	
	private Set<BaggageItem> baggageItems;

	public Set<ConveyorSystem> getConveyorSystems() {
		return conveyorSystems;
	}

	public void setConveyorSystems(Set<ConveyorSystem> conveyorSystems) {
		this.conveyorSystems = conveyorSystems;
	}

	public Set<DepartureSchedule> getDepartureSchedules() {
		return departureSchedules;
	}

	public void setDepartureSchedules(Set<DepartureSchedule> departureSchedules) {
		this.departureSchedules = departureSchedules;
	}

	public Set<BaggageItem> getBaggageItems() {
		return baggageItems;
	}

	public void setBaggageItems(Set<BaggageItem> baggageItems) {
		this.baggageItems = baggageItems;
	}	

}
