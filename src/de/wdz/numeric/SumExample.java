package de.wdz.numeric;

import java.math.BigDecimal;
import java.math.MathContext;

public class SumExample {

	private static int limit = 300;
	private static MathContext context = MathContext.DECIMAL128;

	public static void main(String[] args) {
		for (int i = 0; i < limit; i++) {
			System.out.println(i + " " + Math.ulp(i));
		}
	}

	public static void doubleForward() {
		double sum = 0.0;
		BigDecimal bsum = BigDecimal.ZERO;
		System.out.println("abs. error\t\t\t\t\trel.error");
		for (int i = 1; i <= limit; i++) {
			sum = sum + (1.0 / (new Double(i) * new Double(i)));
			BigDecimal newI = new BigDecimal(i, context);
			bsum = bsum.add(BigDecimal.ONE.divide(newI.multiply(newI, context), context), context);
			System.out.println(i + " " + sum);
			// System.out.println(absoluteError(bsum, new BigDecimal(sum,
			// context)) + "\t\t\t\t\t"
			// + relativeError(bsum, new BigDecimal(sum, context)));
		}
		System.out.println("forward  sum " + sum);
	}

	public static void doubleBackward() {
		double sum = 0.0;
		BigDecimal bsum = BigDecimal.ZERO;
		System.out.println("abs. error\t\t\t\t\trel.error");
		for (int i = limit; i > 0; i--) {
			sum = sum + (1.0 / (new Double(i) * new Double(i)));
			BigDecimal newI = new BigDecimal(i, context);
			bsum = bsum.add(BigDecimal.ONE.divide(newI.multiply(newI, context), context), context);
			System.out.println(i + " " + sum);
			// System.out.println(absoluteError(bsum, new BigDecimal(sum,
			// context)) + "\t\t\t\t\t"
			// + relativeError(bsum, new BigDecimal(sum, context)));
		}
		System.out.println("forward  sum " + sum);
	}

	public static void bigDecimalForward() {
		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 1; i <= limit; i++) {
			BigDecimal newI = new BigDecimal(i);
			sum = sum.add(BigDecimal.ONE.divide(newI.multiply(newI, context), context), context);
		}
		System.out.println("big decimal  forward sum " + sum);
	}

	public static void bigDecimalBackward() {
		BigDecimal sum = BigDecimal.ZERO;
		for (int i = limit; i > 0; i--) {
			BigDecimal newI = new BigDecimal(i);
			sum = sum.add(BigDecimal.ONE.divide(newI.multiply(newI, context), context), context);
		}
		System.out.println("big decimal backward sum " + sum);
	}

	public static double absoulteError(double x, double y) {
		return Math.abs(x) - Math.abs(y);
	}

	public static double relativeError(double x, double y) {
		return (Math.abs(x) - Math.abs(y)) / Math.abs(x);
	}

	public static BigDecimal absoluteError(BigDecimal x, BigDecimal y) {
		return x.abs(context).subtract(y.abs(context), context);
	}

	public static BigDecimal relativeError(BigDecimal x, BigDecimal y) {
		return (x.abs(context).subtract(y.abs(context))).divide(x.abs(context), context);
	}
}