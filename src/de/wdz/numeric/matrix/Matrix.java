package de.wdz.numeric.matrix;

import de.wdz.numeric.matrix.operation.IMatrixOperations;

public class Matrix implements IMatrixOperations {

	private double[][] matrix;

	public Matrix(int size) {
		this.matrix = new double[size][size];
	}

	public Matrix(int x, int y) {
		this.matrix = new double[x][y];
	}

	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public String toString() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		return super.toString();
	}
}