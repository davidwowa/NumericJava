package de.wdz.numeric.matrix.gaussJordan.core;

import de.wdz.numeric.matrix.Matrix;

public class Main {

	public static void main(String[] args) {
		GaussJordanCore core = new GaussJordanCore();

		// int[] sigma = core.getSigma(2, 5, 10);
		// sigma = core.getSigma(0, 13, 20);
		// for (int i = 0; i < sigma.length; i++) {
		// System.out.println("sigma[" + i + "]=" + sigma[i]);
		// }

		Matrix A = core.getTestMatrix9();
		Matrix b = core.getTestVector();
		A.toString();
		System.out.println("--");
		b.toString();
		System.out.println("--");
		core.runAlg5(A, b);

		// GaussAlgorithmus algorithmus = new GaussAlgorithmus();
		// double[] b = { -3, -1, 4 };
		// algorithmus.solve(testMatrix.getMatrix(), b);
	}
}