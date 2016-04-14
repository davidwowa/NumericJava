package de.wdz.numeric.functions.task1;

import java.math.BigDecimal;
import java.math.MathContext;

import de.wdz.numeric.functions.BigDecimalFunctions;
import de.wdz.numeric.functions.DoubleFunctions;
import de.wdz.numeric.util.NumericTools;
import de.wdz.numeric.util.Strategy;

public class Task1 implements DoubleFunctions, BigDecimalFunctions, NumericTools, Strategy {
	private int iterations = 1000;
	private int guess = 10;

	private int limit = 100;

	@Override
	public void runBigDecimal() {
		BigDecimal two = new BigDecimal(2.);
		System.out.println("i, factorial, factorial, absolute error, relative error");
		for (int i = 0; i <= limit; i++) {
			// 2 * pi * n
			BigDecimal underSquare = (two.multiply(pi(), MathContext.DECIMAL128)).multiply(new BigDecimal(i));
			BigDecimal sqrt = sqrt(underSquare, iterations, guess);
			// n/e
			BigDecimal n_e = new BigDecimal(i, MathContext.DECIMAL128).divide(e(), MathContext.DECIMAL128);
			BigDecimal ne_pow = pow(n_e, i);
			BigDecimal result = sqrt.multiply(ne_pow, MathContext.DECIMAL128);
			BigDecimal fac = fac(new BigDecimal(i, MathContext.DECIMAL128));

			System.out.format("%d, %s, %s, %s, %s\n", i, fac.toEngineeringString(), result.toEngineeringString(),
					absoluteError(fac, result).toEngineeringString(), relativeError(fac, result).toEngineeringString());
		}
	}

	@Override
	public void runDouble() {
		double two = 2.;
		System.out.println("i, factorial, factorial, absolute error, relative error");
		for (int i = 0; i <= limit; i++) {
			// 2 * pi * n
			double underSquare = two * Math.PI * i;
			double sqrt = sqrt(underSquare, iterations, guess);
			// n/e
			double n_e = i / Math.E;
			double ne_pow = pow(n_e, i);
			double result = sqrt * ne_pow;
			double fac = fac(i);

			System.out.format("%d, %s, %s, %s, %s\n", i, fac, result, absoluteError(fac, result),
					relativeError(fac, result));
		}
	}

	public static void main(String[] args) {
		Task1 task1 = new Task1();
		task1.runDouble();
		System.out.println("---");
		task1.runBigDecimal();
	}
}