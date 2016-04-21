package de.wdz.numeric.matrix.operation;

import java.util.Arrays;

public interface IMatrixDoubleOperations {

	default void printMatrix(double[][] matrixToShow) {
		if (matrixToShow != null) {
			for (int i = 0; i < matrixToShow.length; i++) {
				for (int j = 0; j < matrixToShow[0].length; j++) {
					System.out.print(matrixToShow[i][j] + " ");
				}
				System.out.print(";\n");
			}
		} else {
			System.out.println("ERROR: create prior a matrix");
		}
	}

	default void printMatrix(double[] matrixToShow) {
		if (matrixToShow != null) {
			for (int i = 0; i < matrixToShow.length; i++) {
				System.out.println(matrixToShow[i] + ", ");
			}
			System.out.print(";\n");
		} else {
			System.out.println("ERROR: create prior a matrix");
		}
	}

	default boolean checkSizeMatrix(double[][] a, double[][] b) {
		if (a != null && b != null) {
			int a_columns = a[0].length;
			int b_rows = b.length;
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
	default double[][] mult(double[][] a, double[][] b) {
		if (checkSizeMatrix(a, b)) {
			// System.out.println(
			// "create new matrix with dimension " + b.getMatrix().length + "x"
			// + a.getMatrix()[0].length);
			double[][] newMatrix = new double[a.length][b[0].length];

			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < b[0].length; j++) {

					double[] array_i = a[i];
					// System.out.println(Arrays.toString(array_i));

					double[] array_j = new double[b.length];
					for (int k = 0; k < array_j.length; k++) {
						array_j[k] = b[k][j];
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
			return newMatrix;
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
	default double[][] permute(int[] sigma, double[][] matrix) {
		System.out.println("<---permute---->");
		printMatrix(matrix);
		System.out.println("with sigma");
		System.out.println(Arrays.toString(sigma));

		double[][] permutetMatrix = new double[matrix.length][matrix.length];
		for (int i = 0; i < sigma.length; i++) {
			double[] tmp = matrix[sigma[i]];
			permutetMatrix[i] = tmp;
		}
		System.out.println("result matrix");
		printMatrix(permutetMatrix);
		matrix = permutetMatrix;
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
	default double[][] scale(int row, double factor, double[][] a) {
		// TODO validate input
		System.out.println("<---scale---->");
		printMatrix(a);
		System.out.println("with factor " + factor + " and row " + row);

		for (int i = 0; i < a[0].length; i++) {
			double tmp = a[row][i];
			tmp = tmp * factor;
			a[row][i] = tmp;
		}
		System.out.println("result matrix");
		printMatrix(a);
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
	 *            Wenn das die Grˆﬂe f¸r die Identity Matrix ist, dann kann man
	 *            es ingorieren, da die Grˆﬂe kann ich von Matrix herausbekommen
	 *            was in Methode-Signatur drin ist
	 * @param matrix
	 *            result Matrix
	 * @return new Matrix
	 */
	default double[][] eliminate(double c, double[][] e_l, double[][] e_k) {
		// TODO validate input
		System.out.println("<---eliminate--->");
		double[][] identityMatrix = getIdentityMatrix(e_l.length);
		// identityMatrix * factor * e_l * e_k
		System.out.println("identity matrix");
		printMatrix(identityMatrix);
		System.out.println("with factor " + c);
		System.out.println("with vector e_l");
		printMatrix(e_l);
		System.out.println("with vector e_k");
		printMatrix(e_k);

		double[][] lk = mult(e_l, e_k);
		double[][] lkc = multWithFactor(c, lk);
		double[][] lkcid = mult(identityMatrix, lkc);

		System.out.println("=");
		printMatrix(lkcid);
		return lkcid;
	}

	default double[][] addRows(int rowIndex1, int rowIndex2, double[][] matrix) {
		System.out.println("<---add rows in matrix---->");
		printMatrix(matrix);
		double[] row1 = matrix[rowIndex1];
		double[] row2 = matrix[rowIndex2];

		System.out.println(Arrays.toString(row1) + " + " + Arrays.toString(row2));
		for (int i = 0; i < row1.length; i++) {
			double tmp = row1[i] + row2[i];
			row2[i] = tmp;
		}
		System.out.println("result matrix");
		printMatrix(matrix);
		return matrix;
	}

	default double[][] addRows(int rowIndex1, int rowIndex2, double factor, double[][] matrix) {
		System.out.println("<---add rows in matrix---->");
		printMatrix(matrix);
		double[] row1 = matrix[rowIndex1];

		// scale row p with s
		for (int i = 0; i < row1.length; i++) {
			row1[i] = row1[i] * factor;
		}

		double[] row2 = matrix[rowIndex2];

		System.out.println(Arrays.toString(row1) + " + " + Arrays.toString(row2));
		for (int i = 0; i < row1.length; i++) {
			double tmp = row1[i] + row2[i];
			row2[i] = tmp;
		}
		System.out.println("result matrix");
		printMatrix(matrix);
		return matrix;
	}

	default double[][] subtractRows(int rowIndex1, int rowIndex2, double[][] matrix) {
		System.out.println("subtract rows in matrix");
		printMatrix(matrix);
		double[] row1 = matrix[rowIndex1];
		double[] row2 = matrix[rowIndex2];

		System.out.println(Arrays.toString(row1) + " - " + Arrays.toString(row2));
		for (int i = 0; i < row1.length; i++) {
			double tmp = row1[i] - row2[i];
			row2[i] = tmp;
		}
		System.out.println("result matrix");
		printMatrix(matrix);
		return matrix;
	}

	/**
	 * Beachte die Reihenfolge
	 */
	default double[][] multWithVector(double[][] matrix, double[][] vector) {
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
	default double[][] multWithFactor(double factor, double[][] matrix) {
		// TODO validate input
		double[][] resultMatrix = new double[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				double tmp = matrix[i][j] * factor;
				resultMatrix[i][j] = tmp;
			}
		}
		return resultMatrix;
	}

	/**
	 * Get identity matrix with diagonale with 1
	 */
	default double[][] getIdentityMatrix(int size) {
		// TODO validate input
		double[][] identityMatrix = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identityMatrix[i][j] = 1.;
				} else {
					identityMatrix[i][j] = 0.;
				}
			}
		}
		return identityMatrix;
	}

	default double[][] getRandomMatrix(int size) {
		double[][] identityMatrix = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identityMatrix[i][j] = Math.random();
				} else {
					identityMatrix[i][j] = Math.random();
				}
			}
		}
		return identityMatrix;
	}

	default double[][] getRandomMatrix(int x, int y) {
		// TODO not tested
		double[][] identityMatrix = new double[x][y];
		for (int i = 0; i < identityMatrix.length; i++) {
			for (int j = 0; j < identityMatrix[0].length; j++) {
				if (i == j) {
					identityMatrix[i][j] = Math.random();
				} else {
					identityMatrix[i][j] = Math.random();
				}
			}
		}
		return identityMatrix;
	}

	default double[][] getTestMatrix() {
		double[][] doubleMatrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix2() {
		double[][] doubleMatrix = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix3() {
		double[][] doubleMatrix = { { 3, 2, 1 }, { 1, 0, 2 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix4() {
		double[][] doubleMatrix = { { 1, 2 }, { 0, 1 }, { 4, 0 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix5() {
		double[][] doubleMatrix = { { 0, 0, 3 }, { 0, 5, 6 }, { 7, 8, 9 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix6() {
		double[][] doubleMatrix = { { 0, 0, 3 }, { 0, 0, 6 }, { 7, 8, 9 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix7() {
		double[][] doubleMatrix = { { 0, 1, 2, 1 }, { 0, 0, 1, 2 }, { 2, 1, 0, 0 }, { 1, 2, 1, 0 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix8() {
		double[][] doubleMatrix = { { 1, 1, -2, -3 }, { 0, 1, -1, -1 }, { 3, -1, 1, 4 } };
		return doubleMatrix;
	}

	default double[][] getTestMatrix9() {
		double[][] doubleMatrix = { { 1, 1, -2 }, { 0, 1, -1 }, { 3, -1, 1 } };
		return doubleMatrix;
	}

	default double[][] getTestVector() {
		double[][] doubleMatrix = { { -3 }, { -1 }, { 4 } };
		return doubleMatrix;
	}

	default double[][] getTestVectorNotizen() {
		double[][] doubleMatrix = { { 4 }, { 8 }, { 2 }, { 1 } };
		return doubleMatrix;
	}
}