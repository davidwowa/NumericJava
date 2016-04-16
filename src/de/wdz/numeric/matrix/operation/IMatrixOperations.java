package de.wdz.numeric.matrix.operation;

import java.util.Arrays;

import de.wdz.numeric.matrix.Matrix;

public interface IMatrixOperations {

	default boolean checkSizeMatrix(Matrix a, Matrix b) {
		if (a != null && b != null) {
			int a_columns = a.getMatrix()[0].length;
			int b_rows = b.getMatrix().length;
			if (a_columns != b_rows) {
				System.out.println(
						"ERROR: number of columns in matrix a is not equal the number of row in matrix b\n see https://de.wikipedia.org/wiki/Matrizenmultiplikation");
			} else {
				return true;
			}
		} else {
			if (a == null) {
				System.out.println("ERROR: matrix a is null");
			}
			if (b == null) {
				System.out.println("ERROR: matrix b is null");
			}
		}
		return false;
	}

	/**
	 * Matrix multiplication
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	default Matrix mult(Matrix a, Matrix b) {
		if (checkSizeMatrix(a, b)) {

			double[][] aMatrix = a.getMatrix();
			double[][] bMatrix = b.getMatrix();
			// System.out.println(
			// "create new matrix with dimension " + b.getMatrix().length + "x"
			// + a.getMatrix()[0].length);
			double[][] newMatrix = new double[aMatrix.length][bMatrix[0].length];

			for (int i = 0; i < aMatrix.length; i++) {
				for (int j = 0; j < bMatrix[0].length; j++) {

					double[] array_i = aMatrix[i];
					// System.out.println(Arrays.toString(array_i));

					double[] array_j = new double[bMatrix.length];
					for (int k = 0; k < array_j.length; k++) {
						array_j[k] = bMatrix[k][j];
					}

					// System.out.println(Arrays.toString(array_j));

					double tmp = 0;
					// System.out.println("tmp + (array_i[k] * array_j[k])");
					for (int k = 0; k < array_j.length; k++) {
						// System.out.println(tmp + "+(array_i[" + array_i[k] +
						// "] * array_j[" + array_j[k] + "])");
						tmp = tmp + (array_i[k] * array_j[k]);
					}
					// System.out.println(tmp);
					newMatrix[i][j] = tmp;
				}
			}
			Matrix resultMatrix = new Matrix();
			resultMatrix.setMatrix(newMatrix);
			return resultMatrix;
		} else {
			System.out.println("ERROR:");
		}
		return null;
	}

	/**
	 * 
	 * @param sigma
	 * @param matrix
	 * @return
	 */
	default Matrix permute(int[] sigma, Matrix matrix) {
		System.out.println("<---permute---->");

		matrix.toString();
		System.out.println("with sigma");
		System.out.println(Arrays.toString(sigma));

		Matrix permutetMatrix = new Matrix(matrix.getMatrix().length);
		for (int i = 0; i < sigma.length; i++) {
			double[] tmp = matrix.getMatrix()[sigma[i]];
			permutetMatrix.getMatrix()[i] = tmp;
		}
		System.out.println("result matrix");
		permutetMatrix.toString();
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
		System.out.println("<---scale---->");
		a.toString();
		System.out.println("with factor " + factor + " and row " + row);

		for (int i = 0; i < a.getMatrix().length; i++) {
			double tmp = a.getMatrix()[row][i];
			tmp = tmp * factor;
			a.getMatrix()[row][i] = tmp;
		}
		System.out.println("result matrix");
		a.toString();
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
	 *            Wenn das die Grˆﬂe f¸r die Identity Matrix ist, dann kann
	 *            man es ingorieren, da die Grˆﬂe kann ich von Matrix
	 *            herausbekommen was in Methode-Signatur drin ist
	 * @param matrix
	 *            result Matrix
	 * @return new Matrix
	 */
	default Matrix eliminate(double c, Matrix e_l, Matrix e_k) {
		// TODO validate input
		System.out.println("<---eliminate--->");
		Matrix identityMatrix = getIdentityMatrix(e_l.getMatrix().length);
		// identityMatrix * factor * e_l * e_k
		System.out.println("identity matrix");
		identityMatrix.toString();
		System.out.println("with factor " + c);
		System.out.println("with vector e_l");
		e_l.toString();
		System.out.println("with vector e_k");
		e_k.toString();

		Matrix lk = mult(e_l, e_k);
		Matrix lkc = multWithFactor(c, lk);
		Matrix lkcid = mult(identityMatrix, lkc);

		System.out.println("=");
		lkcid.toString();
		return lkcid;
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

	default Matrix getRandomMatrix(int x, int y) {
		// TODO not tested
		Matrix identityMatrix = new Matrix(x, y);
		for (int i = 0; i < identityMatrix.getMatrix().length; i++) {
			for (int j = 0; j < identityMatrix.getMatrix()[0].length; j++) {
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

	default Matrix getTestMatrix2() {
		double[][] doubleMatrix = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}

	default Matrix getTestMatrix3() {
		double[][] doubleMatrix = { { 3, 2, 1 }, { 1, 0, 2 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}

	default Matrix getTestMatrix4() {
		double[][] doubleMatrix = { { 1, 2 }, { 0, 1 }, { 4, 0 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}

	default Matrix getTestMatrix5() {
		double[][] doubleMatrix = { { 0, 0, 3 }, { 0, 5, 6 }, { 7, 8, 9 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}

	default Matrix getTestMatrix6() {
		double[][] doubleMatrix = { { 0, 0, 3 }, { 0, 0, 6 }, { 7, 8, 9 } };
		Matrix resultMatrix = new Matrix();
		resultMatrix.setMatrix(doubleMatrix);
		return resultMatrix;
	}

	default Matrix getTestMatrix7() {
		double[][] doubleMatrix = { { 0, 1, 2, 1 }, { 0, 0, 1, 2 }, { 2, 1, 0, 0 }, { 1, 2, 1, 0 } };
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