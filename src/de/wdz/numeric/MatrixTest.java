package de.wdz.numeric;

import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore3;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class MatrixTest implements IMatrixDoubleOperations {

	public static void main(String[] args) {
		GJCore3 core = new GJCore3();
		MatrixGenerator generator = new MatrixGenerator();
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[][] inverse = generator.getIdentityMatrix(matrix.length);

		RealMatrix realMatrix = new BlockRealMatrix(matrix);
		SingularValueDecomposition decomposition = new SingularValueDecomposition(realMatrix);
		System.out.println("condNumber " + decomposition.getConditionNumber());

		double[][] b = { { 0 }, { 0 }, { 0 } };

		core.forwardSubstitution(matrix, b, inverse);
		core.backwardSubstitution(matrix, b, inverse);
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