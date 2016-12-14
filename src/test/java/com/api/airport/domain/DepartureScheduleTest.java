package com.api.airport.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.domain.DepartureSchedule;
import com.api.airport.baggagesystem.exception.DepartureScheduleDataException;
import com.api.airport.baggagesystem.exception.DepartureScheduleParseException;

public class DepartureScheduleTest {
	
	private static final String DepartureScheduleSection = "# Section: Departures\n"
	 		+ "UA10 A1 MIA 08:00\n";
	
	private static final String DepartureScheduleSection_IncorrectFields = "# Section: Departures\n"
	 		+ "UA10 A1 MIA BBB 08:00\n";
	
	private static final String DepartureScheduleSection_IncorrectData = "# Section: Departures\n"
	 		+ "UA10 A1 MIA TUE\n";
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetBaggageItems() {
		
		Set<DepartureSchedule> departureSchedules = DepartureSchedule.getDepartureSchedule(DepartureScheduleSection);
		
		assertNotNull(departureSchedules);		
		assertTrue(departureSchedules.stream().anyMatch((x) -> x.getFlightId().equals("UA10") && x.getFlightGate().equals("A1")));
		
		// Verify ARRIVAL flight is added
		assertTrue(departureSchedules.stream().anyMatch((x) -> x.getFlightId().equals(BaggageConstants.ARRIVAL_FLIGHT_ID) && x.getFlightGate().equals(BaggageConstants.BAGGAGE_CLAIM_GATE)));
	}
	
	@Test(expected = DepartureScheduleDataException.class)
	public void testGetBaggageItemsException_IncorrectFields() {
		DepartureSchedule.getDepartureSchedule(DepartureScheduleSection_IncorrectFields);
	}
	
	@Test(expected = DepartureScheduleParseException.class)
	public void testGetBaggageItemsException_IncorrectData() {
		DepartureSchedule.getDepartureSchedule(DepartureScheduleSection_IncorrectData);
	}

}
