package com.kostua.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private CalculatorApplication controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}