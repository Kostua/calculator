package com.kostua.calculator;

import com.kostua.calculator.data.CarrierRepository;
import com.kostua.calculator.domain.Carrier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Entry point for run Spring Boot application
 *
 * @SpringBootApplication is a meta-annotation that pulls in component scanning, autoconfiguration, and property support.
 */
@SpringBootApplication
public class CalculatorApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	/**
	 * Data loader command line runner.
	 * Save in repository demo data of Carrier class
	 *
	 * @param carrierRepository the carrier repository
	 * @return the command line runner
	 */
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