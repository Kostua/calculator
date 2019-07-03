package com.kostua.calculator.service;

import com.kostua.calculator.domain.CarrierCostCalculationRequest;
import com.kostua.calculator.domain.CarrierCostCalculationResponse;

import java.util.List;

public interface CalculatorService {
    List<CarrierCostCalculationResponse> calculateCarrierCost(CarrierCostCalculationRequest request);
}
