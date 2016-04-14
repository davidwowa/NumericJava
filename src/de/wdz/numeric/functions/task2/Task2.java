package de.wdz.numeric.functions.task2;

import java.math.BigDecimal;
import java.math.MathContext;

import de.wdz.numeric.functions.BigDecimalFunctions;
import de.wdz.numeric.functions.DoubleFunctions;
import de.wdz.numeric.util.NumericTools;
import de.wdz.numeric.util.Strategy;

public class Task2 implements DoubleFunctions, BigDecimalFunctions, NumericTools, Strategy {
	private int iterations = 1000;

	private BigDecimal testValue = BigDecimal.ONE;
	private double testValueD = 1.;

	private int limit = 100;

	@Override
	public void runBigDecimal() {
		BigDecimal ten = new BigDecimal(10, MathContext.DECIMAL128);
		System.out.println("i, tan, sec^2, absolute error, relative error");
		for (int i = 0; i <= limit; i++) {
			int k = (-1) * i;
			BigDecimal h = pow(ten, k);
			BigDecimal xh = testValue.add(h, MathContext.DECIMAL128);

			BigDecimal fxh = tan(xh, iterations);
			BigDecimal fx = tan(testValue, iterations);

			BigDecimal fxh_fx = fxh.subtract(fx, MathContext.DECIMAL128);
			BigDecimal result = fxh_fx.divide(h, MathContext.DECIMAL128);
			BigDecimal sec_2 = sec_2(testValue, iterations);

			System.out.format("%d, %s, %s, %s, %s\n", i, result.toEngineeringString(), sec_2.toEngineeringString(),
					absoluteError(sec_2, result).toEngineeringString(),
					relativeError(sec_2, result).toEngineeringString());
		}
	}

	@Override
	public void runDouble() {
		double ten = 10.;
		System.out.println("i, tan, sec^2, absolute error, relative error");
		for (int i = 0; i <= limit; i++) {
			int k = (-1) * i;
			double h = pow(ten, k);
			double xh = testValueD + h;
			double fxh = tan(xh, iterations);
			double fx = tan(testValueD, iterations);
			double fxh_fx = fxh - fx;
			double result = fxh_fx / h;
			double sec_2 = sec_2(testValueD, iterations);
			System.out.format("%d, %s, %s, %s, %s\n", i, result, sec_2, absoluteError(sec_2, result),
					relativeError(sec_2, result));
		}
	}

	public static void main(String[] args) {
		Task2 task2 = new Task2();
		task2.runDouble();
		System.out.println("---");
		// task2.runBigDecimal();
	}
}