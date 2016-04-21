package de.wdz.numeric.matrix;

public class Matrix {

	private double[][] matrix;

	public Matrix() {
	}

	public Matrix(int size) {
		this.matrix = new double[size][size];
	}

	public Matrix(int x, int y) {
		this.matrix = new double[x][y];
	}

	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public Matrix(double[] vector) {
		matrix = new double[1][vector.length];
		for (int i = 0; i < vector.length; i++) {
			matrix[0][i] = vector[i];
		}
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public String toString() {
		if (matrix != null) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.print(";\n");
			}
			return super.toString();
		} else {
			System.out.println("ERROR: create prior a matrix");
		}
		return null;
	}
}