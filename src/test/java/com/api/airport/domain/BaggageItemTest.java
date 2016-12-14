package com.api.airport.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.baggagesystem.domain.DepartureSchedule;
import com.api.airport.baggagesystem.exception.BaggageItemDataException;
import com.api.airport.baggagesystem.exception.InputDataException;

public class BaggageItemTest {
	
	Set<DepartureSchedule> departureSchedules;
	
	private static final String bagSection = "# Section: Bags\n"
	 		+ "0003 A2 UA10\n"
	 		+ "0004 A8 UA18\n";
	
	private static final String bagSection_IncorrectFields = "# Section: Bags\n"
	 		+ "00 03 A2 UA10\n"
	 		+ "00 04 A8 UA18\n";
	
	private static final String bagSection_BadData = "# Section: Bags\n"
	 		+ "0003 A2 BBBB\n"
	 		+ "0004 A8 AAAA\n";
	
	@Before
	public void setUp() {
		departureSchedules = new HashSet<DepartureSchedule>();
		departureSchedules.add(new DepartureSchedule("UA10","A1", "MIA", null));
		departureSchedules.add(new DepartureSchedule("UA18","A5", "LAX", null));
	}
	
	@Test
	public void testGetBaggageItems() {
		Set<BaggageItem> baggages = BaggageItem.getBaggageItems(bagSection, departureSchedules);
		assertNotNull(baggages);
		assertTrue(baggages.stream().anyMatch((x) -> x.getFlightId().equals("UA10") && x.getEndPoint().equals("A1")));
		assertTrue(baggages.stream().anyMatch((x) -> x.getFlightId().equals("UA18") && x.getEndPoint().equals("A5")));
	}
	
	@Test(expected = InputDataException.class)
	public void testGetBaggageItemsException_NullSection() {
		BaggageItem.getBaggageItems(null, departureSchedules);
	}
	
	@Test(expected = InputDataException.class)
	public void testGetBaggageItemsException_IncorrectFields() {
		BaggageItem.getBaggageItems(bagSection_IncorrectFields, departureSchedules);
	}
	
	@Test(expected = BaggageItemDataException.class)
	public void testGetBaggageItemDataException() {
		BaggageItem.getBaggageItems(bagSection_BadData, departureSchedules);
	}

}
