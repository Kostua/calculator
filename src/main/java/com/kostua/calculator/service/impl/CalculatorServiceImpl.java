package com.kostua.calculator.service.impl;

import com.kostua.calculator.data.CarrierRepository;
import com.kostua.calculator.domain.Carrier;
import com.kostua.calculator.domain.CarrierCostCalculationRequest;
import com.kostua.calculator.domain.CarrierCostCalculationResponse;
import com.kostua.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Calculator service.
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private CarrierRepository carrierRepository;


    public List<CarrierCostCalculationResponse> calculateCarrierCost(CarrierCostCalculationRequest request) {
        List<Carrier> carrier = new ArrayList<>(getAllCarrier());
        List<CarrierCostCalculationResponse> carrierCostCalculationResponses = new ArrayList<>();

        for(Carrier carriers : carrier){


            CarrierCostCalculationResponse response = new CarrierCostCalculationResponse();
            double carrierCost = carriers.getAir() * request.getWeight();
            response.setCarrierCost(roundOff(carrierCost));
            response.setCarrierName(carriers.getName());
            carrierCostCalculationResponses.add(response);

        }
        return carrierCostCalculationResponses;
    }

    /**
     * Get all carrier list.
     *
     * @return the list
     */
    public List<Carrier> getAllCarrier(){
        List<Carrier> carriers = (List<Carrier>) carrierRepository.findAll();


        return carriers;


        }

    private double roundOff(double x) {
        long val = Math.round(x * 100); // cents

        return val / 100.0;
    }


    }

