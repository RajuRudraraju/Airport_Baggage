package com.api.airport.adapter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.api.airport.baggagesystem.adapter.OptimalRoute;
import com.api.airport.baggagesystem.adapter.RouteFinder;
import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.domain.InputTestData;

/**
 * @author Vijay
 */
public class RouteFinderTest {

	private RouteFinder routeFinder;
	
	@Before
	public void setUp() {
		routeFinder = new RouteFinder();
	}
	
	@Test
	public void testGetOptimalRoute() {		
		Map<BaggageItem, OptimalRoute> optimalRoutes = routeFinder.getOptimalRoute(InputTestData.getInputTestData());		
		assertNotNull(optimalRoutes);	
		assertTrue(optimalRoutes.values().toArray()[0].toString().equals(InputTestData.getOutputData()[0]));	
	}
	
	
}
