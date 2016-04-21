package de.wdz.numeric.matrix.gj.core;

public class Main {

	public static void main(String[] args) {
		testCase2();
	}

	/**
	 * example in class
	 * 
	 * Richtige Ergebnise MIT Pivotisierung
	 */
	public static void testCase1() {
		GJCore2 core = new GJCore2();

		double[][] A = core.getTestMatrix7();
		core.printMatrix(A);
		double[][] b = core.getTestVectorNotizen();
		core.printMatrix(b);
		core.forwardSubstitution(A, b);
		core.backSubstitution(A, b);
		core.printMatrix(b);
		// core.runGaussSimple(A, b);
	}

	/**
	 * example in book
	 * 
	 * Richtige Ergebnise OHNE Pivotisierung :-(
	 */
	public static void testCase2() {
		GJCore2 core = new GJCore2();

		double[][] A = core.getTestMatrix9();
		core.printMatrix(A);
		double[][] b = core.getTestVector();
		core.printMatrix(b);
		core.forwardSubstitution(A, b);
		core.backSubstitution(A, b);
		core.printMatrix(b);
		// core.runGaussSimple(A, b);
	}
}