package test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
	
	public double add(double a, double b) {
		return a + b;
	}

	public double subtract(double a, double b) {
		return a - b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public double divide(double a, double b) {
		if (b == 0) {
			System.out.print("Division by zero is not allowed.");
		}
		return a / b;
	}

	public static void main(String[] args) {

		Calculator calc = new Calculator();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter first number: ");
			double a = sc.nextDouble();
			System.out.print("Enter Second Number: ");
			double b = sc.nextDouble();

			System.out.print("Enter an opeartor(+, -, *, /): ");
			String operator = sc.next();
			double result=0;
			switch (operator) {
				case "+":
					result=calc.add(a, b);
					System.out.println("Addition: " + result);
					break;
				case "-":
					result=calc.subtract(a, b);
					System.out.println("Subtraction: " + result);
					break;
				case "*":
					result= calc.multiply(a,b);
					System.out.println("Multiplication:" + result);
					break;
				case "/":
					System.out.println("Division: " + calc.divide(a, b));
					break;
				default:
					System.out.println("Enter a valaid operator!!");
					break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter a valid number " + e);

		}
		sc.close();

	}

}
