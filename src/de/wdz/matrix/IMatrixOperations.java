package de.wdz.matrix;

public interface IMatrixOperations {

	/**
	 * Matrix multiplication
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	default Matrix mult(Matrix a, Matrix b) {
		// TODO validate input
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

	/**
	 * 
	 * @param sigma
	 * @param matrix
	 * @return
	 */
	default Matrix permute(int[] sigma, Matrix matrix) {
		// TODO validate input
		Matrix permutetMatrix = new Matrix(matrix.getMatrix().length);
		for (int i = 0; i < sigma.length; i++) {
			double[] tmp = matrix.getMatrix()[sigma[i]];
			permutetMatrix.getMatrix()[i] = tmp;
		}
		return permutetMatrix;
	}

	/**
	 * Page 70 in Book
	 * 
	 * @param row
	 * @param factor
	 * @param a
	 * @return scaled matrix
	 */
	default Matrix scale(int row, double factor, Matrix a) {
		// TODO validate input
		for (int i = 0; i < a.getMatrix().length; i++) {
			double tmp = a.getMatrix()[row][i];
			tmp = tmp * factor;
			a.getMatrix()[row][i] = tmp;
		}
		return a;
	}

	/**
	 * Elimination (Page 70 in Book)
	 * 
	 * @param c
	 *            the factor
	 * @param e_l
	 * @param e_k
	 * @param n
	 *            Wenn das die Größe für die Identity Matrix ist, dann kann man
	 *            es ingorieren, da die Größe kann ich von Matrix herausbekommen
	 *            was in Methode-Signatur drin ist
	 * @param matrix
	 *            result Matrix
	 * @return new Matrix
	 */
	default Matrix eliminate(int c, Matrix e_l, Matrix e_k, Matrix matrix) {
		// TODO validate input
		// identityMatrix * factor * e_l * e_k
		Matrix identityMatrix = matrix.getIdentityMatrix(matrix.getMatrix().length);
		Matrix identityMatrixMutlC = identityMatrix.multWithFactor(c, identityMatrix);
		Matrix identityMatrixMultCMultE_L = identityMatrixMutlC.multWithVector(identityMatrixMutlC, e_l);
		Matrix identityMatrixMultCMultE_LMultE_K = identityMatrixMultCMultE_L.multWithVector(identityMatrixMultCMultE_L,
				e_k);
		return identityMatrixMultCMultE_LMultE_K;
	}

	/**
	 * Beachte die Reihenfolge
	 */
	default Matrix multWithVector(Matrix matrix, Matrix vector) {
		// TODO validate input
		return mult(matrix, vector);
	}

	/**
	 * Multiplication with Factor
	 * 
	 * @param factor
	 * @param matrix
	 * @return
	 */
	default Matrix multWithFactor(double factor, Matrix matrix) {
		// TODO validate input
		Matrix resultMatrix = new Matrix(matrix.getMatrix().length);
		for (int i = 0; i < matrix.getMatrix().length; i++) {
			for (int j = 0; j < matrix.getMatrix().length; j++) {
				double tmp = matrix.getMatrix()[i][j] * factor;
				resultMatrix.getMatrix()[i][j] = tmp;
			}
		}
		return resultMatrix;
	}

	/**
	 * Get identity matrix with diagonale with 1
	 */
	default Matrix getIdentityMatrix(int size) {
		// TODO validate input
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
		// TODO validate input
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

	default Matrix getTestMatrix() {
		double[][] doubleMatrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}

	default Matrix getTestVector() {
		double[][] doubleMatrix = { { 3 }, { 6 }, { 9 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}
}