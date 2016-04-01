package de.wdz.task1;

import de.wdz.functions.BigDecimalFunctions;
import de.wdz.functions.DoubleFunctions;
import de.wdz.num.NumericTools;

public class Task1 implements DoubleFunctions, BigDecimalFunctions, NumericTools {
	public static void main(String[] args) {
		int iterations = 100;
		int guess = 10;

		double pi = pi(100000000);
		System.out.println(pi);
		System.out.println(Math.PI);
	}

	public static double pi(int tropfenzahl) {
		double pi = 0;
		int innerhalb = 0;
		int gesamt = tropfenzahl;

		while (gesamt > 0) { // generiere Tropfen und addiere je nach
								// Zugehörigkeit
			double dotx = Math.random();
			double doty = Math.random();

			if (dotx * dotx + doty * doty <= 1) {
				// Punkt liegt innerhalb des Kreises
				innerhalb++;
			} else {
				// Punkt liegt außerhalb des Kreises
			}

			gesamt--;
		}

		pi = 4 * (double) innerhalb / tropfenzahl;
		return pi;
	}
}