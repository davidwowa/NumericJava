package de.wdz;

import de.wdz.functions.DoubleFunctions;

public class TestDoubleFunctions implements DoubleFunctions {

	public static void main(String[] args) {
		TestDoubleFunctions test = new TestDoubleFunctions();

		int fac = 100;
		int pow = 100;
		int pow_2 = 2;
		int testNumber = 1;
		int iterations = 10000;

		double fac_res = test.fac(fac);
		System.out.println("fac " + fac_res);

		double double2 = test.pow(pow, pow_2);
		System.out.println("pow " + double2);

		double sin = test.sin(testNumber, iterations);
		System.out.println("+sin \t" + sin);
		System.out.println("-sin \t" + Math.sin(testNumber));

		double cos = test.cos(testNumber, iterations);
		System.out.println("+cos \t" + cos);
		System.out.println("-cos \t" + Math.cos(testNumber));

		double exp = test.exp(testNumber, iterations);
		System.out.println("+exp \t" + exp);
		System.out.println("-exp \t" + Math.exp(testNumber));

		double tan = test.tan(testNumber, iterations);
		System.out.println("+tan \t" + tan);
		System.out.println("-tan \t" + Math.tan(testNumber));

		double sec = test.sec(testNumber, iterations);
		System.out.println("+sec \t" + sec);
		System.out.println("-sec \t" + (1 / Math.cos(testNumber)));

		double sec_2 = test.sec_2(testNumber, iterations);
		System.out.println("+sec_2 \t" + sec_2);
		System.out.println("-sec_2 \t" + Math.pow((1 / Math.cos(testNumber)), pow_2));
	}
}