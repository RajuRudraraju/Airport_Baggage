package com.api.airport.baggagesystem.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.exception.ConveyorSystemDataException;
import com.api.airport.baggagesystem.exception.InputDataException;

/**
 * @author Vijay 
 * This is a domain class for Conveyer System
 */
public class ConveyorSystem {

	private final String startGate;

	private final String endGate;

	private final int travelTime;

	public ConveyorSystem(String startGate, String endGate, int travelTime) {
		this.startGate = startGate;
		this.endGate = endGate;
		this.travelTime = travelTime;
	}

	public String getStartGate() {
		return startGate;
	}

	public String getEndGate() {
		return endGate;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public static Set<ConveyorSystem> getConveyorSystems(String section) {
		Set<ConveyorSystem> conveyorSystems = new HashSet<ConveyorSystem>();
		getSections(section)
				.stream()
				.distinct()
				.forEach(item -> {
					ConveyorSystem conveyorSystem = parseConveyorSystem(item);
					conveyorSystems.add(conveyorSystem);
					// Add Reverse paths
					conveyorSystems.add(new ConveyorSystem(conveyorSystem.getEndGate(), conveyorSystem.getStartGate(), conveyorSystem.getTravelTime()));
					}
				);
		return conveyorSystems;
	}

	private static ConveyorSystem parseConveyorSystem(String inputString) {
		if (inputString == null) {
			throw new InputDataException();
		}
		String[] tokens = inputString.split(BaggageConstants.SPACE_DELIMETER);
		if (tokens.length != 3) {
			throw new ConveyorSystemDataException(
					"Wrong number of fields in Conveyor System Section for line Item: '" + inputString + "'");
		}
		return new ConveyorSystem(tokens[0], tokens[1],	Integer.parseInt(tokens[2].trim()));
	}

	private static List<String> getSections(String section) {
		String[] tokens = section.split("\n");
		return Arrays.asList(tokens).subList(1, tokens.length);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConveyorSystem conveyorSystem = (ConveyorSystem) o;
		return endGate.equals(conveyorSystem.endGate)
				&& startGate.equals(conveyorSystem.startGate);
	}

	@Override
	public int hashCode() {
		int result = startGate.hashCode();
		result = 31 * result + endGate.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return startGate + " " + endGate + " " + travelTime;
	}

}
