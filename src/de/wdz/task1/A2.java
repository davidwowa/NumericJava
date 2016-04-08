package de.wdz.task1;

public class A2 {
	// Näherungswerte für die Ableitung einer Funktion berechnen
	// f'(x) = (f(x +h) - f(x)) / h
	// Funktion = tan(), Vergleichsfunktion = sec^2(x)
	// h = 10 ^(-k) mit k = 0 ... 16

	public static double ableitung(int k) {
		double h = Math.pow(10., -1 * k);
		return (Math.tan(1. + h) - Math.tan(1.) / h);
	}

	public static double sec2(int k) {
		return Math.pow((1. / Math.cos(k)), 2.);
	}

	public static void main(String[] args) {
		System.out.println("k           ableitung'(x)               sec^2(x)");
		for (int k = 0; k < 17; k++)
			System.out.format("%2d %40.15f %30.18f %40.18f\n", k, ableitung(k), sec2(1), sec2(1) - ableitung(k));
	}
}