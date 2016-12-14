package com.api.airport.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.api.airport.baggagesystem.domain.ConveyorSystem;
import com.api.airport.baggagesystem.exception.ConveyorSystemDataException;

public class ConveyorSystemTest {
	
	private static final String ConveyorSystemSection = "# Section: Conveyor System\n"
	 		+ "Concourse_A_Ticketing A5 5\n";
	
	private static final String ConveyorSection_IncorrectFields = "# Section: Conveyor System\n"
	 		+ "Concourse_A_Ticketing A5\n";
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetBaggageItems() {
		Set<ConveyorSystem> conveyorSystems = ConveyorSystem.getConveyorSystems(ConveyorSystemSection);		
		assertNotNull(conveyorSystems);		
		assertTrue(conveyorSystems.stream().anyMatch((x) -> x.getStartGate().equals("Concourse_A_Ticketing") && x.getEndGate().equals("A5")));
		assertTrue(conveyorSystems.stream().anyMatch((x) -> x.getStartGate().equals("A5") && x.getEndGate().equals("Concourse_A_Ticketing")));
	}
	
	@Test(expected = ConveyorSystemDataException.class)
	public void testGetBaggageItemsException_NullSection() {
		ConveyorSystem.getConveyorSystems(ConveyorSection_IncorrectFields);
	}

}
