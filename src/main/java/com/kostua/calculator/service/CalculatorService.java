package com.kostua.calculator.service;

import com.kostua.calculator.domain.CarrierCostCalculationRequest;
import com.kostua.calculator.domain.CarrierCostCalculationResponse;

import java.util.List;

/**
 * The interface Calculator service.
 */
public interface CalculatorService {
    /**
     * Calculate carrier cost list.
     *
     * @param request the request
     * @return the list
     */
    List<CarrierCostCalculationResponse> calculateCarrierCost(CarrierCostCalculationRequest request);
}
