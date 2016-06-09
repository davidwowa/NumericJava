package de.wdz.numeric;

import Jama.Matrix;
import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class MatrixTest implements IMatrixDoubleOperations {

	public static void main(String[] args) {
		GJCore core = new GJCore();
		MatrixGenerator generator = new MatrixGenerator();
		// double[][] matrix = { { 1, 1, 3 }, { 1, 2, 3 }, { 1, 3, 4 } };
		//
		// double[][] matrix2 = { { 21.0, 105.0, 717.5 }, { 105.0, 717.5, 5512.5
		// }, { 717.5, 5512.5, 45166.625 } };

		System.out.println("n " + "\t" + " mustiplications " + "\t" + " time ");
		int bound = 100_000_000;
		// for (int i = 1; i <= bound; i++) {
		// long startTime = System.currentTimeMillis();
		// core.mult(generator.getRandomMatrix(i),
		// generator.getRandomMatrix(i));
		// long endTime = System.currentTimeMillis();
		// System.out.println(i + "," + (endTime - startTime));
		// }

		for (int i = 1; i <= bound; i++) {
			Matrix a = new Matrix(generator.getRandomMatrix(i));
			Matrix b = new Matrix(generator.getRandomMatrix(i));
			long startTime = System.currentTimeMillis();
			a.times(b);
			long endTime = System.currentTimeMillis();
			System.out.println(i + "," + (endTime - startTime));
		}

		// double[][] inverse = generator.getIdentityMatrix(matrix2.length);
		//
		// RealMatrix realMatrix = new BlockRealMatrix(matrix2);
		// SingularValueDecomposition decomposition = new
		// SingularValueDecomposition(realMatrix);
		// System.out.println("condNumber " +
		// decomposition.getConditionNumber());
		//
		// double[][] b = { { 0 }, { 0 }, { 0 } };
		//
		// core.forwardSubstitution(matrix2, b, inverse);
		// core.backwardSubstitution(matrix2, b, inverse);
	}

	public static void test() {
		MatrixTest matrixTest = new MatrixTest();
		MatrixGenerator generator = new MatrixGenerator();
		double[][] A = generator.getRandomMatrix(4, 9);
		// double[][] B = generator.getRandomMatrix(4, 9);
		double[][] C = generator.getRandomMatrix(9, 100);
		// double[][] D = generator.getRandomMatrix(4, 100);

		matrixTest.mult(A, C);

		System.out.println("---");
		matrixTest.printMatrix(A);
		System.out.println("---");
		matrixTest.printMatrix(C);
	}
}