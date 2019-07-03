package com.kostua.calculator.api;

import com.kostua.calculator.data.CarrierRepository;
import com.kostua.calculator.domain.Carrier;
import com.kostua.calculator.domain.CarrierCostCalculationRequest;
import com.kostua.calculator.service.impl.CalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CalculatorController {

    @Autowired
    CalculatorServiceImpl calculatorService;

    @Autowired
    private CarrierRepository carrierRepository;

    @GetMapping("/carrier")
    public List<Carrier> getAllCarrier() {
        return (List<Carrier>) carrierRepository.findAll();
    }

    @PostMapping("/calc")
    public ResponseEntity calculateCarrierCost(@RequestBody CarrierCostCalculationRequest request) {
        return ResponseEntity.ok().body(calculatorService.calculateCarrierCost(request));

    }


    }

