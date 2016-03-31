package de.wdz;

import java.math.BigDecimal;
import java.math.MathContext;

import de.wdz.functions.BigDecimalFunctions;

public class TestBigDecimalFunctions implements BigDecimalFunctions {

	public static void main(String[] args) {
		TestBigDecimalFunctions test = new TestBigDecimalFunctions();

		int fac = 100;
		int pow = 100;
		int pow_2 = 2;
		int testNumber = 1;
		int iterations = 1000;

		BigDecimal bigDecimal = test.fac(new BigDecimal(fac));
		System.out.println("fac " + bigDecimal);

		BigDecimal bigDecimal2 = test.pow(new BigDecimal(pow), pow_2);
		System.out.println("pow " + bigDecimal2);

		BigDecimal sin = test.sin(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+sin \t" + sin);
		System.out.println("-sin \t" + Math.sin(testNumber));

		BigDecimal cos = test.cos(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+cos \t" + cos);
		System.out.println("-cos \t" + Math.cos(testNumber));

		BigDecimal exp = test.exp(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+exp \t" + exp);
		System.out.println("-exp \t" + Math.exp(testNumber));

		BigDecimal tan = test.tan(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+tan \t" + tan);
		System.out.println("-tan \t" + Math.tan(testNumber));

		BigDecimal sec = test.sec(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+sec \t" + sec);
		System.out.println("-sec \t" + (1 / Math.cos(testNumber)));

		BigDecimal sec_2 = test.sec_2(new BigDecimal(testNumber, MathContext.DECIMAL128), iterations);
		System.out.println("+sec_2 \t" + sec_2);
		System.out.println("-sec_2 \t" + Math.pow((1 / Math.cos(testNumber)), pow_2));
	}
}