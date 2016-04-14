package de.wdz.numeric;

import java.math.BigDecimal;
import java.math.MathContext;

import de.wdz.numeric.functions.BigDecimalFunctions;
import de.wdz.numeric.util.NumericTools;

public class TestBigDecimalFunctions implements BigDecimalFunctions, NumericTools {

	public static void main(String[] args) {
		TestBigDecimalFunctions test = new TestBigDecimalFunctions();

		int fac = 100;
		int pow = 100;
		int pow_2 = 2;
		// TODO only little number, but why ?
		int testNumber = 50;
		int iterations = 1000;
		int guess = 10;

		BigDecimal facResult = test.fac(new BigDecimal(fac));
		System.out.println("fac " + facResult);

		BigDecimal powResult = test.pow(new BigDecimal(pow), pow_2);
		System.out.println("pow " + powResult);

		BigDecimal sin = test.sin(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+sin \t" + sin);
		System.out.println("-sin \t" + Math.sin(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(sin, new BigDecimal(Math.sin(testNumber))));
		System.out.println("relative error \t" + test.relativeError(sin, new BigDecimal(Math.sin(testNumber))));

		BigDecimal cos = test.cos(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+cos \t" + cos);
		System.out.println("-cos \t" + Math.cos(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(cos, new BigDecimal(Math.cos(testNumber))));
		System.out.println("relative error \t" + test.relativeError(cos, new BigDecimal(Math.cos(testNumber))));

		BigDecimal exp = test.exp(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+exp \t" + exp);
		System.out.println("-exp \t" + Math.exp(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(exp, new BigDecimal(Math.exp(testNumber))));
		System.out.println("relative error \t" + test.relativeError(exp, new BigDecimal(Math.exp(testNumber))));

		BigDecimal tan = test.tan(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+tan \t" + tan);
		System.out.println("-tan \t" + Math.tan(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(tan, new BigDecimal(Math.tan(testNumber))));
		System.out.println("relative error \t" + test.relativeError(tan, new BigDecimal(Math.tan(testNumber))));

		BigDecimal sec = test.sec(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+sec \t" + sec);
		System.out.println("-sec \t" + (1 / Math.cos(testNumber)));
		System.out.println("absolute error \t" + test.absoluteError(sec, new BigDecimal((1 / Math.cos(testNumber)))));
		System.out.println("relative error \t" + test.relativeError(sec, new BigDecimal((1 / Math.cos(testNumber)))));

		BigDecimal sec_2 = test.sec_2(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+sec_2 \t" + sec_2);
		System.out.println("-sec_2 \t" + Math.pow((1 / Math.cos(testNumber)), pow_2));
		System.out.println("absolute error \t" + test.absoluteError(sec, new BigDecimal((1 / Math.cos(testNumber)))));
		System.out.println("relative error \t" + test.relativeError(sec, new BigDecimal((1 / Math.cos(testNumber)))));

		BigDecimal sqrt = test.sqrt(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations, guess);
		System.out.println("+sqrt \t" + sqrt);
		System.out.println("-sqrt \t" + Math.sqrt(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(sqrt, new BigDecimal(Math.sqrt(testNumber))));
		System.out.println("relative error \t" + test.relativeError(sqrt, new BigDecimal(Math.sqrt(testNumber))));

		BigDecimal euler = test.euler(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+e \t" + euler);
		System.out.println("-e \t" + Math.E);
		System.out.println("absolute error \t" + test.absoluteError(euler, new BigDecimal(Math.E)));
		System.out.println("relative error \t" + test.relativeError(euler, new BigDecimal(Math.E)));

		BigDecimal pi = test.pi();
		System.out.println("+pi \t" + pi);
		System.out.println("-pi \t" + Math.PI);
		System.out.println("absolute error \t" + test.absoluteError(pi, new BigDecimal(Math.PI)));
		System.out.println("relative error \t" + test.relativeError(pi, new BigDecimal(Math.PI)));
	}
}