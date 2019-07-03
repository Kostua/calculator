package com.kostua.calculator;

import com.kostua.calculator.data.CarrierRepository;
import com.kostua.calculator.domain.Carrier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(CarrierRepository carrierRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				carrierRepository.save(new Carrier("Dnipro", 5.75, 3.50));
				carrierRepository.save(new Carrier("EasyExpress", 8.95, 5.50));
				carrierRepository.save(new Carrier("Meest", 7.70, 5.35));

			}
		};
	}
}