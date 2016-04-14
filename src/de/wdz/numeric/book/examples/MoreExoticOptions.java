package de.wdz.numeric.book.examples;

public class MoreExoticOptions {
	public static void main(String[] args) {
		double x = 100.;
		double result = 0.;
		for (int i = 0; i <= 5; i++) {
			double a = 1. / (x + i);
			result = result + a;
		}
		System.out.println(result);
		// Hier das Problem: Wert: 3218688200 als int ist "out of range"
		// sonst kommt es das selbe raus
		System.out.println(188463347. / 3218688200.);
	}
}