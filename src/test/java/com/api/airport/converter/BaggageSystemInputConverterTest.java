package com.api.airport.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.converter.BaggageSystemInputConverter;
import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.baggagesystem.domain.BaggageSystemInput;
import com.api.airport.baggagesystem.domain.ConveyorSystem;
import com.api.airport.baggagesystem.domain.DepartureSchedule;
import com.api.airport.domain.InputTestData;

public class BaggageSystemInputConverterTest {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetBaggageItems() {
		
		BaggageSystemInput baggageSystemInput = BaggageSystemInputConverter.inputConverter(InputTestData.getInputTestData());
		
		Set<ConveyorSystem> conveyorSystems = baggageSystemInput.getConveyorSystems();		
		assertNotNull(conveyorSystems);		
		assertTrue(conveyorSystems.stream().anyMatch((x) -> x.getStartGate().equals("Concourse_A_Ticketing") && x.getEndGate().equals("A5")));
		assertTrue(conveyorSystems.stream().anyMatch((x) -> x.getStartGate().equals("A5") && x.getEndGate().equals("Concourse_A_Ticketing")));
		
		Set<DepartureSchedule> departureSchedules = baggageSystemInput.getDepartureSchedules();
		assertNotNull(departureSchedules);		
		assertTrue(departureSchedules.stream().anyMatch((x) -> x.getFlightId().equals("UA10") && x.getFlightGate().equals("A1")));		
		assertTrue(departureSchedules.stream().anyMatch((x) -> x.getFlightId().equals(BaggageConstants.ARRIVAL_FLIGHT_ID) && x.getFlightGate().equals(BaggageConstants.BAGGAGE_CLAIM_GATE)));

		Set<BaggageItem> baggages = baggageSystemInput.getBaggageItems();
		assertNotNull(baggages);
		assertTrue(baggages.stream().anyMatch((x) -> x.getFlightId().equals("UA10") && x.getEndPoint().equals("A1")));
		assertTrue(baggages.stream().anyMatch((x) -> x.getFlightId().equals("UA18") && x.getEndPoint().equals("A5")));
	}
}
