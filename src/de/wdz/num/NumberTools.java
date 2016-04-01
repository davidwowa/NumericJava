package de.wdz.num;

public class NumberTools {

	public double pi(int tropfenzahl) {
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
