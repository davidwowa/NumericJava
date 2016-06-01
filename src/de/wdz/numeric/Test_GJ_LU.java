package de.wdz.numeric;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;
import de.wdz.numeric.matrix.lu.core.LUCore;
import de.wdz.numeric.matrix.lu.core.PLUCore;

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
//		testGJCase12();
		// testLUCase1();
		// testLUCase2();
		testInverseCase();
		// testPLUCase3();
		// testTMatrix();
		// testMultMatrix();
		// testLeastSquareMatrixFinal();
		// testLeastSquareMatrix2();
		// polynomialRegressionBookExample();
		// testWithAllKnownVectors();
		// testAddMatrix();
		// regularisationTest();
	}

	public static void testInverseCase() {
		GJCore core = new GJCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixPLUE2xample();
		// getTestMatrixCLUExample
		// getTestMatrixLUExampleMatlab
		core.inverse(A);
}
	
	public static void polynomialRegressionBookExample() {
		GJCore core3 = new GJCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixPolynomialRegressionBookExample();
		double[][] b = generator.getVectorPolynomialRegressionBookExample();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		core3.forwardSubstitution(A, b, inverse);
		core3.backwardSubstitution(A, b, inverse);
	}

	public static void testLeastSquareMatrix2() {
		GJCore core3 = new GJCore();
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
		GJCore core3 = new GJCore();
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

	public static void testAddMatrix() {
		GJCore core3 = new GJCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getMatrixPolynomialRegressionBookExample();

		core3.printMatrix(A);

		System.out.println("--");
		double[][] newMatrix = core3.add(A, A);

		core3.printMatrix(newMatrix);
	}

	public static void regularisationTest() {
		GJCore core = new GJCore();
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

		System.out.println("(At * A + c*I)");
		double[][] I = generator.getIdentityMatrix(AtTimesA.length);
		double[][] cI = core.multWithFactor(Math.pow(0.5, 2), I);
		double[][] toSolve = core.add(AtTimesA, cI);

		System.out.println("At * b");
		double[][] newb = core.mult(rAtMatrix, b);

		double[][] inverse = generator.getIdentityMatrix(toSolve.length);

		core.forwardSubstitution(toSolve, newb, inverse);
		core.backwardSubstitution(toSolve, newb, inverse);
	}

	public static void testLeastSquareMatrixFinal() {
		GJCore core = new GJCore();
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
		double[][] newb = core.mult(rAtMatrix, b);

		double[][] inverse = generator.getIdentityMatrix(AtTimesA.length);

		core.forwardSubstitution(AtTimesA, newb, inverse);
		core.backwardSubstitution(AtTimesA, newb, inverse);
	}

	public static List<double[][]> getAllVectors(List<double[][]> bs) {
		List<double[][]> result = new ArrayList<>();
		for (double[][] ds : bs) {
			GJCore core = new GJCore();
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
			double[][] value = core.mult(rAtMatrix, b);

			// System.out.println("PREPARE MATRIX WITH " + value[0][0]);
			// double[][] forGJ = core.multWithFactor(value[0][0], AtTimesA);

			double[][] inverse = generator.getIdentityMatrix(AtTimesA.length);

			core.forwardSubstitution(AtTimesA, value, inverse);
			core.backwardSubstitution(AtTimesA, value, inverse);

			result.add(ds);
		}
		return result;
	}

	public static void testTMatrix() {
		GJCore core3 = new GJCore();
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
		GJCore core3 = new GJCore();
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
		GJCore core = new GJCore();
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
		// getTestMatrixCLUExample
		// getTestMatrixLUExampleMatlab
		core.U(A);
		System.out.println("U-MATRIX");
		core.printMatrix(core.getU());

		core.L();
	}

	public static void testLUCase2() {
		LUCore core = new LUCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixLUExample();
		// getTestMatrixCLUExample
		// getTestMatrixLUExampleMatlab
		core.U(A);
		core.L();

		System.out.println("RESULT");
		System.out.println("L");

		core.printMatrix(core.getL());

		System.out.println("U");

		core.printMatrix(core.getU());

		System.out.println("TEST");

		double[][] test = core.mult(core.getL(), core.getU());

		core.printMatrix(test);
	}

	public static void testPLUCase3() {
		PLUCore core = new PLUCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixPLUExample();
		// getTestMatrixBookExample
		// getTestMatrixCLUExample
		// getTestMatrixLUExampleMatlab
		core.U(A);
		core.L();
		core.calculateP();

		System.out.println("RESULT");
		System.out.println("P");

		core.printMatrix(core.getP());

		System.out.println("L");

		core.printMatrix(core.getL());

		System.out.println("U");

		core.printMatrix(core.getU());

		System.out.println("TEST");

		double[][] test = core.mult(core.getP(), core.getL());
		double[][] result = core.mult(test, core.getU());

		core.printMatrix(result);
	}

	public static void testLUCase3() {
		LUCore core = new LUCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixPLUE2xample();
		// getTestMatrixCLUExample
		// getTestMatrixLUExampleMatlab
		core.U(A);
		core.L();

		System.out.println("RESULT");
		System.out.println("L");

		core.printMatrix(core.getL());

		System.out.println("U");

		core.printMatrix(core.getU());

		System.out.println("TEST");

		double[][] test = core.mult(core.getL(), core.getU());

		core.printMatrix(test);
	}

	public static void testGJCase12() {
		GJCore core = new GJCore();
		MatrixGenerator generator = new MatrixGenerator();

		double[][] A = generator.getTestMatrixPLUExample();

		System.out.println("--START--");

		core.printMatrix(A);

		core.forwardSubstitution(A);
		 A = core.adjustMatrix(A);
		// core.backwardSubstitution(A);
		System.out.println("--END--");
		core.printMatrix(A);
	}

	public static void testGJCase10() {
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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
		GJCore core = new GJCore();
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