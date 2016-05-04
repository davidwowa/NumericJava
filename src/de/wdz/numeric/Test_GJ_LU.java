package de.wdz.numeric;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore2;
import de.wdz.numeric.matrix.gj.core.GJCore3;
import de.wdz.numeric.matrix.lu.core.LUCore;

public class Test_GJ_LU {

	public static void main(String[] args) {
		// testGJCaseRandom();// ok
		// testGJCase1(); // problem with pivot element
		// testGJCase2(); // ok, but other order in result vector
		// testGJCase3();// ok, but order of rows in start matrix was reordered
		// testGJCase4();// ok
		// testGJCase5();// ok, but last row before backward substitution is
		// missing
		// testGJCase6(); // ok
		// testGJCase7();
		// testGJCase8();
		// testGJCase9();
		// testGJCase10();
		testGJCase11();
		// testLUCase1();
	}

	public static void testGJCase11() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixSquares();

		System.out.println("--START--");

		core.printMatrix(A);

		core.mult(A, A);

		System.out.println("--END--");
	}

	public static void testLUCase1() {
		LUCore core = new LUCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixCLUExample();

		System.out.println("--START--");

		core.printMatrix(A);

		core.U(A);
		core.L();

		System.out.println("--END--");
		core.printMatrix(A);

	}

	public static void testGJCase10() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixLittle();

		System.out.println("--START--");

		core.printMatrix(A);

		core.forwardSubstitution(A);
		A = core.adjustMatrix(A);
		// core.backwardSubstitution(A);
		System.out.println("--END--");
		core.printMatrix(A);
	}

	public static void testGJCase9() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixLittle();

		System.out.println("--START--");

		core.printMatrix(A);

		core.forwardSubstitution(A);
		core.backwardSubstitution(A);

		System.out.println("--END--");
		core.printMatrix(A);
	}

	public static void testGJCase8() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixLong();
		double[][] b = generator.getMatrixLongVector();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		System.out.println("--START--");

		core.printMatrix(A);

		core.forwardSubstitution(A, b, inverse);
		core.backwardSubstitution(A, b, inverse);

		System.out.println("--END--");
		core.printMatrix(A);
	}

	public static void testGJCase7() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixLong();

		System.out.println("--START--");

		core.printMatrix(A);

		core.forwardSubstitution(A);
		core.backwardSubstitution(A);

		System.out.println("--END--");
		core.printMatrix(A);
	}

	public static void testGJCase6() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixBookExample();
		double[][] b = generator.getTestVectorBookExample();
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

	public static void testGJCaseRandom() {
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

	public static void testGJCase5() {
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

	public static void testGJCase4() {
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

	public static void testGJCase3() {
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

	public static void testGJCase2() {
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

	public static void testGJCase1() {
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
}