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
		double[][] inverse = core.getIdentityMatrix(A.length);
		core.forwardSubstitution(A, b, inverse);
		core.backSubstitution(A, b, inverse);
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
		// core.printMatrix(A);
		double[][] b = core.getTestVector();
		// core.printMatrix(b);
		// double[][] inverse = core.getIdentityMatrix(A.length);
		// core.printMatrix(inverse);
		// core.forwardSubstitution(A, b, inverse);
		// core.backwardSubstitution(A, b, inverse);
		// core.printMatrix(b);
		// core.printMatrix(A);
		// core.printMatrix(inverse);
		core.U(A, b);
		core.L();
		// core.runGaussSimple(A, b);
	}
}