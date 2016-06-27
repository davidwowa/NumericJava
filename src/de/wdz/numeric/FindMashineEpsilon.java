package de.wdz.numeric;

import java.math.BigDecimal;
import java.math.MathContext;

public class FindMashineEpsilon {
	public static void main(String[] args) {
		floatEps();
		doubleEps();
		// bigDecimalEps();
	}

	/**
	 * TODO !!!
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
		double eps = 1.0;
		int counter = 0;

		while (eps + 1.0 != 1.0) {
			counter = counter + 1;
			eps = eps / 2.0;
		}
		System.out.println("double mashine eps = " + eps);
		System.out.println("double counter = " + counter);
	}

	public static void floatEps() {
		float eps = 1;
		int counter = 0;

		while (eps + 1.0 != 1.0) {
			counter = counter + 1;
			eps = eps / 2;
		}
		System.out.println("float mashine eps = " + eps);
		System.out.println("float counter = " + counter);
	}
}