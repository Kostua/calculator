package com.kostua.calculator.api;

import com.kostua.calculator.data.CarrierRepository;
import com.kostua.calculator.domain.Carrier;
import com.kostua.calculator.domain.CarrierCostCalculationRequest;
import com.kostua.calculator.service.impl.CalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class implement REST controller
 *
 * @RestController indicates that the data returned by each method
 * will be written straight into the response body instead of rendering a template.
 *
 * @Autowired Spring's dependency injection
 */
@RequestMapping("/api")
@RestController
public class CalculatorController {

    @Autowired
    CalculatorServiceImpl calculatorService;

    @Autowired
    private CarrierRepository carrierRepository;

    /**
     * Gets all carrier.
     *
     * @return the all carrier
     */
    @GetMapping("/carrier")
    public List<Carrier> getAllCarrier() {
        return (List<Carrier>) carrierRepository.findAll();
    }

    /**
     * Calculate carrier cost response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/calc")
    public ResponseEntity calculateCarrierCost(@RequestBody CarrierCostCalculationRequest request) {
        return ResponseEntity.ok().body(calculatorService.calculateCarrierCost(request));

    }


    }

