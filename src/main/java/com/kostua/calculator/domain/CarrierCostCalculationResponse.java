package com.kostua.calculator.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarrierCostCalculationResponse {

    private Double carrierCost;
    private String carrierName;
}
