package de.wdz.numeric;

import de.wdz.numeric.function.DoubleFunctions;
import de.wdz.numeric.util.NumericTools;

public class TestDoubleFunctions implements DoubleFunctions, NumericTools {

	public static void main(String[] args) {
		TestDoubleFunctions test = new TestDoubleFunctions();

		int fac = 100;
		int pow = 100;
		int pow_2 = 2;
		// TODO only little number, but why ?
		int testNumber = 1;
		int iterations = 1000;
		int guess = 10;

		double fac_res = test.fac(fac);
		System.out.println("fac " + fac_res);

		double double2 = test.pow(pow, pow_2);
		System.out.println("pow " + double2);

		double sin = test.sin(testNumber, iterations);
		System.out.println("+sin \t" + sin);
		System.out.println("-sin \t" + Math.sin(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(sin, Math.sin(testNumber)));
		System.out.println("relative error \t" + test.relativeError(sin, Math.sin(testNumber)));

		double cos = test.cos(testNumber, iterations);
		System.out.println("+cos \t" + cos);
		System.out.println("-cos \t" + Math.cos(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(cos, Math.cos(testNumber)));
		System.out.println("relative error \t" + test.relativeError(cos, Math.cos(testNumber)));

		double exp = test.exp(testNumber, iterations);
		System.out.println("+exp \t" + exp);
		System.out.println("-exp \t" + Math.exp(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(exp, Math.exp(testNumber)));
		System.out.println("relative error \t" + test.relativeError(exp, Math.exp(testNumber)));

		double tan = test.tan(testNumber, iterations);
		System.out.println("+tan \t" + tan);
		System.out.println("-tan \t" + Math.tan(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(tan, Math.tan(testNumber)));
		System.out.println("relative error \t" + test.relativeError(tan, Math.tan(testNumber)));

		double sec = test.sec(testNumber, iterations);
		System.out.println("+sec \t" + sec);
		System.out.println("-sec \t" + (1 / Math.cos(testNumber)));
		System.out.println("absolute error \t" + test.absoluteError(sec, (1 / Math.cos(testNumber))));
		System.out.println("relative error \t" + test.relativeError(sec, (1 / Math.cos(testNumber))));

		double sec_2 = test.sec_2(testNumber, iterations);
		System.out.println("+sec_2 \t" + sec_2);
		System.out.println("-sec_2 \t" + Math.pow((1 / Math.cos(testNumber)), pow_2));
		System.out
				.println("absolute error \t" + test.absoluteError(sec_2, Math.pow((1 / Math.cos(testNumber)), pow_2)));
		System.out
				.println("relative error \t" + test.relativeError(sec_2, Math.pow((1 / Math.cos(testNumber)), pow_2)));

		double sqrt = test.sqrt(testNumber, iterations, guess);
		System.out.println("+sqrt \t" + sqrt);
		System.out.println("-sqrt \t" + Math.sqrt(testNumber));
		System.out.println("absolute error \t" + test.absoluteError(sqrt, Math.sqrt(testNumber)));
		System.out.println("relative error \t" + test.relativeError(sqrt, Math.sqrt(testNumber)));

		double euler = test.euler(testNumber, iterations);
		System.out.println("+e \t" + euler);
		System.out.println("-e \t" + Math.E);
		System.out.println("absolute error \t" + test.absoluteError(euler, Math.E));
		System.out.println("relative error \t" + test.relativeError(euler, Math.E));
	}
}