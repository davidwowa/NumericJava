package de.wdz.matrix;

public interface IMatrixOperations {

	default Matrix mult(Matrix a, Matrix b) {

		double[][] resultMatrix = null;

		if (a.getMatrix().length == b.getMatrix().length) {
			int row_a = a.getMatrix().length;
			int column_a = a.getMatrix()[0].length;
			int column_b = b.getMatrix()[0].length;

			resultMatrix = new double[row_a][column_b];

			for (int i = 0; i < row_a; i++) {
				for (int j = 0; j < column_b; j++) {
					resultMatrix[i][j] = 0;
					for (int k = 0; k < column_a; k++) {
						resultMatrix[i][j] += a.getMatrix()[i][k] * b.getMatrix()[k][j];
					}
				}
			}
		} else {
			int rows = a.getMatrix().length;
			int columns = b.getMatrix()[0].length;

			resultMatrix = new double[rows][columns];
			for (int i = 0; i < a.getMatrix().length; i++) {
				for (int j = 0; j < a.getMatrix()[0].length; j++) {
					resultMatrix[i][j] = 0;
				}
			}
		}
		Matrix result = new Matrix(resultMatrix);
		return result;
	}

	default Matrix permute(int[] sigma, Matrix matrix) {
		Matrix permutetMatrix = new Matrix(matrix.getMatrix().length);
		for (int i = 0; i < sigma.length; i++) {
			double[] tmp = matrix.getMatrix()[sigma[i]];
			permutetMatrix.getMatrix()[i] = tmp;
		}
		return permutetMatrix;
	}

	default Matrix scale(int row, double factor, Matrix a) {
		for (int i = 0; i < a.getMatrix().length; i++) {
			double tmp = a.getMatrix()[row][i];
			tmp = tmp * factor;
			a.getMatrix()[row][i] = tmp;
		}
		return a;
	}

	// c nur als integer
	default Matrix eliminate(int c, double[] l, double[] k, int n) {
		return null;
	}

	default Matrix getIdentityMatrix(int size) {
		Matrix identityMatrix = new Matrix(size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identityMatrix.getMatrix()[i][j] = 1.;
				} else {
					identityMatrix.getMatrix()[i][j] = 0.;
				}
			}
		}
		return identityMatrix;
	}

	default Matrix getRandomMatrix(int size) {
		Matrix identityMatrix = new Matrix(size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identityMatrix.getMatrix()[i][j] = Math.random();
				} else {
					identityMatrix.getMatrix()[i][j] = Math.random();
				}
			}
		}
		return identityMatrix;
	}
}