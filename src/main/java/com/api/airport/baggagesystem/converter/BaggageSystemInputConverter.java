package com.api.airport.baggagesystem.converter;

import com.api.airport.baggagesystem.constants.BaggageConstants;
import com.api.airport.baggagesystem.domain.BaggageItem;
import com.api.airport.baggagesystem.domain.BaggageSystemInput;
import com.api.airport.baggagesystem.domain.ConveyorSystem;
import com.api.airport.baggagesystem.domain.DepartureSchedule;
import com.api.airport.baggagesystem.exception.InvalidInputException;

/**
 * @author Vijay
 */
public class BaggageSystemInputConverter {

	public static BaggageSystemInput inputConverter(String inputString) {
		if (inputString == null) {
			throw new InvalidInputException();
		}
		
		String[] sectionsTokens = inputString.split(BaggageConstants.SECTION_DELIMETER);

		if (sectionsTokens.length != 4) {
			throw new InvalidInputException(
					"Wrong number of sections. Invalid input data.");
		}

		BaggageSystemInput baggageSystemInput = new BaggageSystemInput();
		baggageSystemInput.setConveyorSystems(ConveyorSystem.getConveyorSystems(sectionsTokens[1]));
		baggageSystemInput.setDepartureSchedules(DepartureSchedule.getDepartureSchedule(sectionsTokens[2]));
		baggageSystemInput.setBaggageItems(BaggageItem.getBaggageItems(sectionsTokens[3], baggageSystemInput.getDepartureSchedules()));
		
		return baggageSystemInput;
	}

}
