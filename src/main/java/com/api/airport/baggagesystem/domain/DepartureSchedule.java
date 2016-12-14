package com.api.airport.baggagesystem.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.exception.DepartureScheduleDataException;
import com.api.airport.baggagesystem.exception.DepartureScheduleParseException;
import com.api.airport.baggagesystem.exception.InputDataException;

/**
 * @author Vijay
 * This is a domain class for departure schedule
 */
public class DepartureSchedule {
	
	private final String flightId;
	
	private final String flightGate;
	
	private final String destination;
	
	private final Date flightTime;
	
	public DepartureSchedule(String flightId, String flightGate, String destination, Date flightTime) {
		this.flightId = flightId;
		this.flightGate = flightGate;
		this.destination = destination;
		this.flightTime = flightTime;
	}

	public String getFlightId() {
		return flightId;
	}

	public String getFlightGate() {
		return flightGate;
	}

	public String getDestination() {
		return destination;
	}

	public Date getFlightTime() {
		return flightTime;
	}	

	/**
	 * Convert and parse DepartureSchedule section
	 * @param section
	 * @return Set<DepartureSchedule>
	 */
	public static Set<DepartureSchedule> getDepartureSchedule(String section) {
		Set<DepartureSchedule> departureSchedules = new HashSet<DepartureSchedule>();
		getSections(section)
				.stream()
				.distinct()
				.forEach(
						item -> departureSchedules
								.add(parseDepartureSchedule(item)));
		departureSchedules.add(new DepartureSchedule(BaggageConstants.ARRIVAL_FLIGHT_ID, BaggageConstants.BAGGAGE_CLAIM_GATE, null, null));
		return departureSchedules;
	}

	/**
	 * Parse DepartureSchedule
	 * @param inputString
	 * @return DepartureSchedule
	 */
	private static DepartureSchedule parseDepartureSchedule(String inputString) {
		if (inputString == null) {
			throw new InputDataException();
		}
		String[] tokens = inputString.split(BaggageConstants.SPACE_DELIMETER);
		if (tokens.length != 4) {
			throw new DepartureScheduleDataException(
					"Wrong number of fields in Departure Schedule Section for line Item: '" + inputString + "'");			
		}
		try {
			return new DepartureSchedule(tokens[0], tokens[1], tokens[2],
					new SimpleDateFormat(BaggageConstants.DATE_FORMAT)
							.parse(tokens[3]));
		} catch (ParseException e) {
			throw new DepartureScheduleParseException("Could not parse flight time.");
		}
	}
	
	private static List<String> getSections(String section) {
		String[] tokens = section.split("\n");
		return Arrays.asList(tokens).subList(1, tokens.length);
	}	

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && flightId.equals(((DepartureSchedule) o).flightId);
    }

    @Override
    public int hashCode() {
        return flightId.hashCode();
    }

    @Override
    public String toString() {
        return flightId + " " + flightGate + " " + destination + " " + flightTime;
    }

}
