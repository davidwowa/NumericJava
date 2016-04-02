package de.wdz.task3;

import java.math.BigDecimal;
import java.math.MathContext;

import de.wdz.functions.BigDecimalFunctions;
import de.wdz.functions.DoubleFunctions;
import de.wdz.num.NumericTools;
import de.wdz.num.Strategy;

public class Task3 implements DoubleFunctions, BigDecimalFunctions, NumericTools, Strategy {
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

			BigDecimal x_add_h = testValue.add(h, MathContext.DECIMAL128);
			BigDecimal x_minus_h = testValue.subtract(h, MathContext.DECIMAL128);

			BigDecimal fx_add_h = tan(x_add_h, iterations);
			BigDecimal fx_minus_h = tan(x_minus_h, iterations);

			BigDecimal dif = fx_add_h.subtract(fx_minus_h, MathContext.DECIMAL128);
			BigDecimal result = dif.divide((new BigDecimal("2").multiply(h)), MathContext.DECIMAL128);

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

			double x_add_h = testValueD + h;
			double x_minus_h = testValueD - h;

			double fx_add_h = tan(x_add_h, iterations);
			double fx_minus_h = tan(x_minus_h, iterations);

			double dif = fx_add_h - fx_minus_h;
			double result = dif / (2. * h);

			double sec_2 = sec_2(testValueD, iterations);
			System.out.format("%d, %s, %s, %s, %s\n", i, result, sec_2, absoluteError(sec_2, result),
					relativeError(sec_2, result));
		}
	}

	public static void main(String[] args) {
		Task3 task3 = new Task3();
		// task3.runDouble();
		System.out.println("---");
		task3.runBigDecimal();
	}
}