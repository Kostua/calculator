package com.kostua.calculator.api;

import com.kostua.calculator.data.CarrierRepository;
import com.kostua.calculator.domain.CarrierCostCalculationRequest;
import com.kostua.calculator.service.impl.CalculatorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Class implement REST controller
 *
 * @RestController indicates that the data returned by each method
 * will be written straight into the response body instead of rendering a template.
 *
 * @Autowired Spring's dependency injection
 */
@Controller
public class CalculatorController {

    
@Autowired CalculatorServiceImpl calculatorService;
@Autowired private CarrierRepository carrierRepository;
    
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    /**
     * Gets all carrier.
     *
     * @return the all carrier

    @GetMapping("/carrier")
    public List<Carrier> getAllCarrier() {
        return (List<Carrier>) carrierRepository.findAll();
    }

    
     * Calculate carrier cost response entity.
     *
     * @param request the request
     * @return the response entity
*/
    @PostMapping("/api/calc")
    public ResponseEntity calculateCarrierCost(@RequestBody CarrierCostCalculationRequest request) {
        return ResponseEntity.ok().body(calculatorService.calculateCarrierCost(request));

    }


    }