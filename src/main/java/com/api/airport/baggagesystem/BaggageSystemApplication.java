package com.api.airport.baggagesystem;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import com.api.airport.baggagesystem.adapter.OptimalRoute;
import com.api.airport.baggagesystem.adapter.RouteFinder;
import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.baggagesystem.exception.InputFileException;
import com.api.airport.baggagesystem.exception.InputFileNotFoundException;

import static java.nio.file.Files.readAllBytes;

/* 
 * @author Vijay
 */
public class BaggageSystemApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length == 0) {
            System.out.println("Please provide the input file.");
            throw new InputFileNotFoundException(
					"Could not find input file.");
        }
		
		String inputString = null;
        try {
             inputString = new String(readAllBytes(Paths.get(args[0])));
        } catch (IOException ioe) {
        	System.out.println("Could not read input file.");
			throw new InputFileException(
					"Could not read input file.");
        }
        
        Map<BaggageItem, OptimalRoute> optimalRoutes = new RouteFinder().getOptimalRoute(inputString);
        
        optimalRoutes.forEach((bagItem, optimalRoute) -> System.out.println(bagItem.getBagNumber() + BaggageConstants.SPACE_DELIMETER + optimalRoute));

    }

}
