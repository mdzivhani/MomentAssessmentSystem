package com.assessment.momentum.services;

import java.util.ArrayList;
import java.util.Arrays;

public class AdvancedCalculations {

	private static final double PI = Math.PI;
	private static final double E = Math.E;
	private static final int RIGHT_HAND_SIDE = 1;
	private static final int LEFT_HAND_SIDE = -1;

	public String advancedCalculator(String equation) {
		int position = 0;
		equation = replaceConstants(equation);

		// calculate equation inside brackets
		if (-1 != (position = equation.indexOf("("))) {
			String subexp = getEquationInBrackets(equation, position);
			equation = equation.replace("(" + subexp + ")", advancedCalculator(subexp));
			return advancedCalculator(equation);
		}

		else if (-1 != (position = equation.indexOf("exp"))) {
			position += 2;
			String number = getNumber(equation, position, RIGHT_HAND_SIDE);
			equation = equation.replace("exp" + number, Double.toString(Math.exp(Double.parseDouble(number))));
			return advancedCalculator(equation);
		}

		else if (-1 != (position = equation.indexOf("root"))) {
			return advancedCalculator(root(equation, position));
		}

		// calculates the power of a number
		else if (-1 != (position = equation.indexOf("^"))) {
			return advancedCalculator(power(equation, position));
		}
		// calculate division and multiplication before addition and subtraction
		else if (equation.indexOf("*") > 0 | equation.indexOf("/") > 0) {
			return advancedCalculator(divideMultiply(equation, position));

			// calculate addition and subtraction
		} else if (equation.indexOf("+") > 0 | equation.indexOf("-") > 0) {
			return advancedCalculator(addSubtraction(equation, position));

		} else
			return equation;
	}

	private String root(String equation, int position) {
		char operator = equation.charAt(position);
		String rootValues = getNumber(equation, position, RIGHT_HAND_SIDE);
		equation = equation.replace("root", "");
		int commaPos = equation.indexOf(",");
		String leftNum = getNumber(equation, commaPos, LEFT_HAND_SIDE);
		String rightNum = getNumber(equation, commaPos, RIGHT_HAND_SIDE);

		double num1 = Double.parseDouble(leftNum);
		double num2 = Double.parseDouble(rightNum);
		double total = 0;
		if (num2 == 2) {
			total = Math.sqrt(num1);
		} else if (num2 == 3) {
			total = Math.cbrt(num1);
		}
		return equation.replace(leftNum + "," + rightNum, Double.toString(total));
	}

	private String divideMultiply(String equation, int position) {
		int dividePosition = equation.indexOf("/");
		int multiplyPosition = equation.indexOf("*");
		position = Math.min(multiplyPosition, dividePosition);
		if (multiplyPosition < 0)
			position = dividePosition;
		else if (dividePosition < 0)
			position = multiplyPosition;
		return basicCalculator(equation, position);
	}

	private String addSubtraction(String equation, int position) {
		int addPosition = equation.indexOf("+");
		int minusPosition = equation.indexOf("-");

		position = Math.min(addPosition, minusPosition);

		if (addPosition < 0)
			position = minusPosition;
		else if (minusPosition < 0)
			position = addPosition;
		return basicCalculator(equation, position);
	}

	/*
	 * Calculates the power
	 */
	private String power(String equation, int position) {
		char operator = equation.charAt(position);
		String leftNum = getNumber(equation, position, LEFT_HAND_SIDE);
		String rightNum = getNumber(equation, position, RIGHT_HAND_SIDE);
		double num1 = Double.parseDouble(leftNum);
		double num2 = Double.parseDouble(rightNum);
		double total = Math.pow(num1, num2);
		return equation.replace(leftNum + operator + rightNum, Double.toString(total));
	}

	/*
	 * Calculates the equation using (*,/,+, -)
	 */
	private String basicCalculator(String equation, int position) {
		BasicCalculations basicCalculations = new BasicCalculations();
		char operator = equation.charAt(position);
		String leftNum = getNumber(equation, position, LEFT_HAND_SIDE);
		String rightNum = getNumber(equation, position, RIGHT_HAND_SIDE);
		double num1 = Double.parseDouble(leftNum);
		double num2 = Double.parseDouble(rightNum);
		// using basic calculations method
		String total = Double.toString(basicCalculations.basicCalculator(num1, String.valueOf(operator), num2));

		return equation.replace(leftNum + operator + rightNum, total);

	}

	/*
	 * Extracts equation inside the bracket
	 */
	private String getEquationInBrackets(String equation, int position) {
		int numberOfBraceOpen = 1;
		String subequation = "";
		for (int i = position + 1; i < equation.length(); i++) {
			switch (equation.charAt(i)) {
			case '(':
				numberOfBraceOpen++;
				subequation += "(";
				break;
			case ')':
				numberOfBraceOpen--;
				if (numberOfBraceOpen != 0)
					subequation += ")";
				break;
			default:
				if (numberOfBraceOpen > 0)
					subequation += equation.charAt(i);
			}
			if (numberOfBraceOpen == 0 && !subequation.equals(""))
				return subequation;
		}
		return "Not all braces are closed";
	}

	/*
	 * Method gets number before and after operators
	 */
	private static String getNumber(String equation, int position, int side) {
		ArrayList<Character> operators = new ArrayList<>(Arrays.asList('+', '-', '/', '*'));
		String number = "";
		int length = equation.length();
		int currPos = position + side;

		if (equation.charAt(currPos) == '-') {
			number += equation.charAt(currPos);
			currPos += side;
		}

		for (; currPos >= 0 && currPos < length && !operators.contains(equation.charAt(currPos)); currPos += side)
			number += equation.charAt(currPos);

		if (side == LEFT_HAND_SIDE)
			number = new StringBuilder(number).reverse().toString();

		if (side == LEFT_HAND_SIDE && equation.startsWith("-"))
			number = "-" + number;

		return number;
	}

	/*
	 * Method replaces worded constants with values
	 */
	private String replaceConstants(String equation) {
		equation = equation.trim().toLowerCase();
		equation = equation.replace(" ", "");
		equation = equation.replace("pi", Double.toString(PI));
		equation = equation.replace("e", Double.toString(E));
		return equation;
	}

}
