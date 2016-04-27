package de.wdz.numeric.matrix.gj.core;

public class Main {

	public static void main(String[] args) {
		testCase5();
	}

	public static void testCase5(){
		GJCore2 core = new GJCore2();

		double[][] A = core.getTestRMatrix2();
		double[][] b = core.getRVector2();
		double[][] inverse = core.getIdentityMatrix(A.length);

		core.printMatrix(A);

		core.forwardSubstitution(A, b, inverse);
//		core.backwardSubstitution(A, b, inverse);
		
		core.printMatrix(A);
//		core.printMatrix(b);
	}
	
	public static void testCase4(){
		GJCore2 core = new GJCore2();

		double[][] A = core.getTestRMatrix();
		double[][] b = core.getRVector();
		double[][] inverse = core.getIdentityMatrix(A.length);

		core.printMatrix(A);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);
		
		core.printMatrix(A);
		core.printMatrix(b);
	}
	
	public static void testCase3() {
		GJCore3 core = new GJCore3();

		double[][] A = core.getTestMatrix8();
		double[][] b = core.getTestVectorNotizen();
		double[][] inverse = core.getIdentityMatrix(A.length);

		core.printMatrix(A);

		core.forwardSubstitution(A, b, inverse);

		System.out.println("..");
		core.printMatrix(A);
	}

	/**
	 * example in class
	 * 
	 * Richtige Ergebnise MIT Pivotisierung
	 */
	public static void testCase1() {
		GJCore3 core = new GJCore3();

		double[][] A = core.getTestMatrix7();
		double[][] b = core.getTestVectorNotizen();
		double[][] inverse = core.getIdentityMatrix(A.length);

		core.forwardSubstitution(A, b, inverse);

		System.out.println("..");
		core.printMatrix(A);
	}

	/**
	 * example in book
	 * 
	 * Richtige Ergebnise OHNE Pivotisierung :-(
	 */
	public static void testCase2() {
		GJCore3 core = new GJCore3();

		final double[][] A = core.getTestMatrix777();
		final double[][] b = core.getTestVectorNotizen();
		final double[][] inverse = core.getIdentityMatrix(A.length);

		core.forwardSubstitution(A, b, inverse);
		System.out.println("..");
		core.printMatrix(A);
	}

	/**
	 * example in book
	 * 
	 * Richtige Ergebnise OHNE Pivotisierung :-(
	 */
	public static void testCase3LU() {
		GJCore3 core = new GJCore3();

		double[][] A = core.getTestMatrix9();
		double[][] b = core.getTestVector();
		// LU

		core.U(A);
		// core.L();
		// core.runGaussSimple(A, b);
	}
}