package de.wdz.numeric;

import java.math.MathContext;

public class CalcualteE {

	private static int limit = 10000;

	public static void main(String[] args) {
		expp();
	}

	public static void expp() {
		for (int i = 1; i < limit; i++) {
			double result = (Math.exp(1d / new Double(i)) - 1d) / (1d / new Double(i));
			System.out.println(result);
		}
	}

	public static void doubleE() {
		double e = 0.0;
		for (int i = 0; i < limit; i++) {
			double newI = new Double(i);
			double tmp = 1d + (1d / newI);
			e = Math.pow(tmp, newI);
			System.out.println(e);
		}
	}
}