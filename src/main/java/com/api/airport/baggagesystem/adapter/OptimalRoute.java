package com.api.airport.baggagesystem.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.baggagesystem.domain.ConveyorSystem;

/**
 * @author Vijay
 */
public class OptimalRoute {
	
	private final Set<ConveyorSystem> conveyorSystems;
	
	private final ConveyorSystem currentConveyorSystem;
	
	private final BaggageItem baggageItem;
	
	private final Set<String> optimalGates = new HashSet<>();
	
	private final List<ConveyorSystem> optimalConveyors = new ArrayList<>();
	
	private final List<OptimalRoute> optimalRoutes = new ArrayList<>();
	
	private boolean isEndPointFound;
	
	private int totalTravelTime = 0;
	
	public OptimalRoute(BaggageItem baggageItem, ConveyorSystem currentConveyorSystem, Set<ConveyorSystem> conveyorSystems) {
		this.baggageItem = baggageItem;
		this.currentConveyorSystem = currentConveyorSystem;
		this.conveyorSystems = conveyorSystems;
	}	
	

	/**
	 * Traverse all connected gates and aggregate totalTime with corresponding optimal route
	 */
    public void extract() {
    	optimalConveyors.add(currentConveyorSystem);
    	optimalGates.add(currentConveyorSystem.getStartGate());
        String endGate = currentConveyorSystem.getEndGate();
        optimalGates.add(endGate);
        totalTravelTime += currentConveyorSystem.getTravelTime();
        if (endGate.equals(baggageItem.getEndPoint())) {
        	isEndPointFound = true;
        } else {        	
        	conveyorSystems.stream()
        	.filter((item) -> item.getStartGate().equals(endGate))
        	.filter((item) -> !optimalGates.contains(item.getEndGate()))
        	.forEach(this::search);       	
        }
    }    

    /**
     * Save each traversed Gate
     * @param conveyorSystem
     */
    public void search(ConveyorSystem conveyorSystem) {
    	OptimalRoute optimalRoute = new OptimalRoute(baggageItem, conveyorSystem, conveyorSystems);
    	optimalRoute.totalTravelTime = totalTravelTime;
    	optimalRoute.optimalGates.addAll(optimalGates);
    	optimalRoute.optimalConveyors.addAll(optimalConveyors);
    	optimalRoute.extract();
    	optimalRoutes.add(optimalRoute);
    }
    
    public List<OptimalRoute> getPossibleRoutes() {
        return optimalRoutes;
    }    

    public int getTotalTravelTime() {
        return totalTravelTime;
    }
    
    public boolean isEndPointFound() {
        return isEndPointFound;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        optimalConveyors.forEach(conveyorSystem -> builder.append(conveyorSystem.getStartGate()).append(BaggageConstants.SPACE_DELIMETER));
        builder.append(optimalConveyors.get(optimalConveyors.size() - 1).getEndGate()).append(" : ").append(totalTravelTime);
        return builder.toString();
    }

}
