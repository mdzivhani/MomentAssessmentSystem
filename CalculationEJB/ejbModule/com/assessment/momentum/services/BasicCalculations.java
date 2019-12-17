package com.assessment.momentum.services;

public class BasicCalculations {

	public double basicCalculator(double number1, String arithmeticOperatior, double number2) {
		switch (arithmeticOperatior.trim()) {
		case "+":
			return number1 + number2;
		case "-":
			return number1 - number2;
		case "*":
			return number1 * number2;
		case "/":
			return number1 / number2;
		}
	
		return 0;
	}

}
