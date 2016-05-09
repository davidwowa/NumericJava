package de.wdz.numeric;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class MatrixTest implements IMatrixDoubleOperations {

	public static void main(String[] args) {
		MatrixTest matrixTest = new MatrixTest();
		MatrixGenerator generator = new MatrixGenerator();
		double[][] A = generator.getRandomMatrix(4, 9);
		double[][] B = generator.getRandomMatrix(4, 9);
		double[][] C = generator.getRandomMatrix(9, 100);
		double[][] D = generator.getRandomMatrix(4, 100);
		
		matrixTest.mult(A, C);
		
		System.out.println("---");
		matrixTest.printMatrix(A);
		System.out.println("---");
		matrixTest.printMatrix(C);
	}
}