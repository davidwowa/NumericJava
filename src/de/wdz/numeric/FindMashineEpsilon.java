package de.wdz.numeric;

import java.math.BigDecimal;
import java.math.MathContext;

public class FindMashineEpsilon {
	public static void main(String[] args) {
		floatEps();
		doubleEps();
		// bigDecimalEps();
		// smallest numer
		System.out.println("smallest number " + Math.pow(2, -1022));
		System.out.println("double min_norm " + Double.MIN_NORMAL);
		double result = (1.d - Math.pow(2, -53)) * Math.pow(2, 1024);
		System.out.println("biggest number " + result);
		System.out.println("double max_val " + Double.MAX_VALUE);
	}

	/**
	 * TODO !!!danger
	 */
	public static void bigDecimalEps() {
		MathContext context = MathContext.DECIMAL32;
		BigDecimal eps = BigDecimal.ONE;
		BigDecimal two = new BigDecimal("2", context);
		int counter = 0;

		while (!(eps.add(BigDecimal.ONE, context)).equals(BigDecimal.ONE)) {
			counter = counter + 1;
			eps = eps.divide(two, context);
			System.out.println(eps);
		}
		System.out.println("BigDecimal mashine eps = " + eps);
		System.out.println("BigDecimal counter = " + counter);
	}

	public static void doubleEps() {
		double eps = 0.5;
		int counter = 0;

		while (eps + 1.0 != 1.0) {
			counter = counter + 1;
			eps = eps / 2.0;
		}
		System.out.println("double mashine eps = " + eps);
		System.out.println("double counter = " + counter);

		for (int i = 1; i < 5; i++) {
			double realEps = new Double(i) * eps;
			System.out.println(i + " " + (1 + realEps) + " real eps " + realEps);
		}
	}

	public static void floatEps() {
		float eps = 0.5f;
		int counter = 0;

		while (eps + 1.0f != 1.0f) {
			counter = counter + 1;
			eps = eps / 2f;
		}
		System.out.println("float mashine eps = " + eps);
		System.out.println("float counter = " + counter);
	}
}