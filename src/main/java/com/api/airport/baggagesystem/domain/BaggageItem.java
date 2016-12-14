package com.api.airport.baggagesystem.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.exception.BaggageItemDataException;
import com.api.airport.baggagesystem.exception.InputDataException;

/**
 * @author Vijay 
 * This is a domain class for Bag List Items
 */
public class BaggageItem {

	private final Long bagNumber;

	private final String entryPoint;

	private final String flightId;

	private final String endPoint;

	public BaggageItem(Long bagNumber, String entryPoint, String flightId,
			String endPoint) {
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
		this.endPoint = endPoint;
	}

	public Long getBagNumber() {
		return bagNumber;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}

	public String getEndPoint() {
		return endPoint;
	}

	/**
	 * Convert and parse BaggageItems 
	 * @param section
	 * @param departureSchedules
	 * @return Set<BaggageItems>
	 */
	public static Set<BaggageItem> getBaggageItems(String section,
			Set<DepartureSchedule> departureSchedules) {
		if (section == null) {
			throw new InputDataException();
		}
		Set<BaggageItem> baggageItemSet = new HashSet<BaggageItem>();
		getSections(section)
				.stream()
				.forEach(
						item -> baggageItemSet.add(parseBaggageItems(item,
								departureSchedules)));
		return baggageItemSet;
	}

	/**
	 * parse baggageItem
	 * @param inputString
	 * @param departureSchedules
	 * @return BaggageItem
	 */
	private static BaggageItem parseBaggageItems(String inputString,
			Set<DepartureSchedule> departureSchedules) {
		if (inputString == null) {
			throw new InputDataException();
		}
		String[] tokens = inputString.split(BaggageConstants.SPACE_DELIMETER);
		if (tokens.length != 3) {
			throw new InputDataException(
					"Wrong number of fields in Bags Section for line Item: '" + inputString + "'");
		}
		
		return new BaggageItem(Long.valueOf(tokens[0]), tokens[1], tokens[2],
				departureSchedules.stream()
						.filter(item -> item.getFlightId().equals(tokens[2].trim()))
						.findAny()
						.orElseThrow(BaggageItemDataException::new)
						.getFlightGate());
	}

	private static List<String> getSections(String section) {
		String[] tokens = section.split("\n");
		return Arrays.asList(tokens).subList(1, tokens.length);
	}

	@Override
	public boolean equals(Object o) {
		return this == o || !(o == null || getClass() != o.getClass())
				&& bagNumber.equals(((BaggageItem) o).bagNumber);
	}

	@Override
	public int hashCode() {
		return bagNumber.hashCode();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(bagNumber).append(" ")
				.append(entryPoint).append(" ").append(flightId).append(" ").append(endPoint).toString();
	}

}
