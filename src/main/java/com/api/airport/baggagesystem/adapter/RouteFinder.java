package com.api.airport.baggagesystem.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.api.airport.baggagesystem.converter.BaggageSystemInputConverter;
import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.baggagesystem.domain.BaggageSystemInput;
import com.api.airport.baggagesystem.domain.ConveyorSystem;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author Vijay
 */
public class RouteFinder {

	public Map<BaggageItem, OptimalRoute> getOptimalRoute(String inputString) {
		
		BaggageSystemInput input = BaggageSystemInputConverter.inputConverter(inputString);
		
		return input.getBaggageItems()
				.stream()
				.collect(toMap((item) -> item, (item) -> processBagItems(item, input.getConveyorSystems())));
	}

	/**
	 * Process each BaggageItem and extract optimal route
	 * @param baggageItem
	 * @param conveyorSystems
	 * @return OptimalRoute
	 */
	public OptimalRoute processBagItems(BaggageItem baggageItem, Set<ConveyorSystem> conveyorSystems) {

		List<OptimalRoute> connectingRoutes = conveyorSystems
				.stream()
				.filter(conveyorSystem -> conveyorSystem.getStartGate().equals(baggageItem.getEntryPoint()))
				.map(conveyorSystem -> new OptimalRoute(baggageItem, conveyorSystem, conveyorSystems))
				.collect(toList());
		
		connectingRoutes.stream().forEach(OptimalRoute::extract);
		
		return getAllConnectedRoutes(connectingRoutes)
                .stream()
                .filter(OptimalRoute::isEndPointFound)
                .min(comparing(OptimalRoute::getTotalTravelTime))
                .get();
	}
	
	/**
	 * Get all the possible connected routes 
	 * @param connectingRoutes
	 * @return List<OptimalRoute>
	 */
    private List<OptimalRoute> getAllConnectedRoutes(List<OptimalRoute> connectingRoutes) {
        List<OptimalRoute> optimalRoutes = new ArrayList<>();
        connectingRoutes.forEach(route -> {
                    if (!route.getPossibleRoutes().isEmpty()) {
                    	optimalRoutes.addAll(getAllConnectedRoutes(route.getPossibleRoutes()));
                    }
                    optimalRoutes.add(route);
                }
        );
        return optimalRoutes;
    }


}
