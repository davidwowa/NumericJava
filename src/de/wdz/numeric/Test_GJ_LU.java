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
		// testGJCase11();
		// testLUCase1();
		// testTMatrix();
		// testMultMatrix();
		testLeastSquareMatrix();
		// testLeastSquareMatrix2();
		// polynomialRegressionBookExample();
	}

	public static void polynomialRegressionBookExample() {
		GJCore3 core3 = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixPolynomialRegressionBookExample();
		double[][] b = generator.getVectorPolynomialRegressionBookExample();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		core3.forwardSubstitution(A, b, inverse);
		core3.backwardSubstitution(A, b, inverse);
	}

	public static void testLeastSquareMatrix2() {
		GJCore3 core3 = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();
		System.out.println("LEAST SQUARE EXERCISE");
		System.out.println("MATRIX A");
		double[][] A = generator.getMatrixSquaresA();
		core3.printMatrix(A);
		System.out.println("MATRIX b");
		double[][] b = generator.getMatrixSquaresb();
		core3.printMatrix(b);
		double[][] At = core3.transpose(A);
		System.out.println("MATRIX At");
		core3.printMatrix(At);

		System.out.println("At * b");
		double[][] forGJ = core3.mult(At, b);
		core3.printMatrix(forGJ);

		core3.forwardSubstitution(forGJ);

		// 1.0
	}

	public static void testLeastSquareMatrixX() {
		GJCore3 core3 = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();
		System.out.println("LEAST SQUARE EXERCISE");
		System.out.println("MATRIX A");
		double[][] A = generator.getMatrixSquaresA();
		core3.printMatrix(A);
		System.out.println("MATRIX b");
		double[][] b = generator.getMatrixSquaresb();
		core3.printMatrix(b);
		// double[][] At = core3.T(A);
		// System.out.println("MATRIX At");
		// core3.printMatrix(At);

		// System.out.println("BUILD NEW A MATRIX");
		// double[][] rMatrix = core3.rebuildMatrixToFormForLeastSquares(A, 3);
		// core3.printMatrix(rMatrix);

		// System.out.println("BUILD NEW At MATRIX");
		// double[][] rAtMatrix = core3.T(rMatrix);
		// core3.printMatrix(rAtMatrix);

		// System.out.println("At * A");
		// double[][] forGJ = core3.mult(rAtMatrix, rMatrix);
		// core3.printMatrix(forGJ);

		// double[][] bb = generator.getMatrixSquaresbb();
		// double[][] bb = {{1.},{1.},{1.}};
		double[][] inverse = generator.getIdentityMatrix(A.length);

		core3.forwardSubstitution(A, b, inverse);
		// core3.backwardSubstitution(forGJ, bb, inverse);

		// 1.0 5.0 34.166666666666664 ;
		// 0.0 1.0 10.000000000000002 ;
		// 0.0 0.0 0.9999999999999999 ;
	}

	public static void testLeastSquareMatrix() {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();
		System.out.println("LEAST SQUARE EXERCISE");

		System.out.println("MATRIX A");
		double[][] A = generator.getMatrixSquaresA();
		core.printMatrix(A);

		System.out.println("MATRIX b");
		double[][] b = generator.getMatrixSquaresb();
		core.printMatrix(b);

		double[][] At = core.transpose(A);
		System.out.println("MATRIX At");
		core.printMatrix(At);

		System.out.println("BUILD NEW A MATRIX");
		double[][] rMatrix = core.rebuildMatrixToFormForLeastSquares(A, 3);
		core.printMatrix(rMatrix);

		System.out.println("BUILD NEW At MATRIX");
		double[][] rAtMatrix = core.transpose(rMatrix);
		core.printMatrix(rAtMatrix);

		System.out.println("At * A");
		double[][] AtTimesA = core.mult(rAtMatrix, rMatrix);
		core.printMatrix(AtTimesA);

		System.out.println("At * b");
		double[][] value = core.mult(At, b);
		
		double[][] forGJ = core.multWithFactor(value[0][0], AtTimesA);
		
		double[][] newb = { { 1 }, { value[0][0] }, { Math.pow(value[0][0], 2) } };
//		double[][] newb = { { Math.pow(value[0][0], 2) }, { value[0][0] }, { 1 } };
//		double[][] newb = { { Math.pow(value[0][0], 2) }, { Math.pow(value[0][0], 2) }, { Math.pow(value[0][0], 2) } };
//		double[][] newb = { { Math.sqrt(value[0][0]) }, { Math.sqrt(value[0][0]) }, { Math.sqrt(value[0][0]) } };

		
		double[][] inverse = generator.getIdentityMatrix(AtTimesA.length);
		
		core.forwardSubstitution(forGJ, newb, inverse);
		core.backwardSubstitution(forGJ, newb, inverse);

	}

	public static void testTMatrix() {
		GJCore3 core3 = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] matrix = generator.getRandomMatrix(3, 2);
		core3.printMatrix(matrix);
		System.out.println("---");
		double[][] t_matrix = core3.transpose(matrix);
		core3.printMatrix(t_matrix);
		System.out.println("mutl matrix");
		double[][] result = core3.mult(matrix, t_matrix);
		core3.printMatrix(result);
	}

	public static void testMultMatrix() {
		GJCore3 core3 = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getRandomMatrix(3, 21);
		System.out.println("--");
		core3.printMatrix(A);
		double[][] B = generator.getRandomMatrix(21, 3);
		System.out.println("--");
		core3.printMatrix(B);
		System.out.println("--");
		double[][] result = core3.mult(A, B);
		core3.printMatrix(result);
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