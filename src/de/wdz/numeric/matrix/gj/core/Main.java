package de.wdz.numeric.matrix.gj.core;

import de.wdz.numeric.matrix.MatrixGenerator;

public class Main {

	public static void main(String[] args) {
		// testCaseRandom();// ok
		// testCase1(); // problem with pivot element
		// testCase2(); // ok, but other order in result vector
		// testCase3();// ok, but order of rows in start matrix was reordered
		// testCase4();// ok
		// testCase5();// ok, but last row before backward substitution is
		// missing
	}

	public static void testCaseRandom() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getRandomMatrix(3);
		double[][] b = generator.getRandomMatrix(3, 1);
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCase6() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestRMatrix2();
		double[][] b = generator.getRVector2();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCase5() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestRMatrix2();
		double[][] b = generator.getRVector2();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCase4() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestRMatrix();
		double[][] b = generator.getRVector();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCase3() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrix777();
		double[][] b = generator.getTestVectorNotizen();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCase2() {
		GJCore2 core = new GJCore2();
		MatrixGenerator generator = new MatrixGenerator();

		final double[][] A = generator.getTestMatrix777();
		final double[][] b = generator.getTestVectorNotizen();
		final double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCase1() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrix7();
		double[][] b = generator.getTestVectorNotizen();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);
		core.printMatrix(b);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
		core.printMatrix(b);
	}

	public static void testCaseLU() {
	}
}