package de.wdz.numeric.matrix.operation;

import java.util.Arrays;

import de.wdz.numeric.matrix.MatrixGenerator;

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
			System.out.println("\t\t\t ERROR: create prior a matrix");
		}
	}

	default void printMatrix(double[] matrixToShow) {
		if (matrixToShow != null) {
			for (int i = 0; i < matrixToShow.length; i++) {
				System.out.println(matrixToShow[i] + ", ");
			}
			System.out.print(";\n");
		} else {
			System.out.println("\t\t\t ERROR: create prior a matrix");
		}
	}

	default double[][] rebuildMatrixToFormForLeastSquaresInv(double[][] matrix, int polynomGrad) {
		if (matrix != null) {
			double[][] newMatrix = new double[matrix.length][polynomGrad];

			// STEP 2: make other columns
			int count = 0;
			for (int i = polynomGrad - 1; i >= 1; i--) {
				for (int j = 0; j < newMatrix.length; j++) {
					newMatrix[j][count] = Math.pow(matrix[j][0], i);
					// System.out.println("newMatrix[" + j + "][" + count + "] =
					// Math.pow(matrix[" + j + "][0], " + i + ")= "
					// + Math.pow(matrix[j][0], i));
				}
				count++;
			}

			// STEP 1: make last column with 1
			for (int i = 0; i < newMatrix.length; i++) {
				newMatrix[i][newMatrix[0].length - 1] = 1.;
			}

			return newMatrix;
		}
		return null;
	}

	default double[][] rebuildMatrixToFormForLeastSquares(double[][] matrix, int polynomGrad) {
		if (matrix != null) {
			double[][] newMatrix = new double[matrix.length][polynomGrad];
			// STEP 1: make fist column with 1
			for (int i = 0; i < newMatrix.length; i++) {
				newMatrix[i][0] = 1.;
			}

			// STEP 2: make other columns
			for (int i = 1; i < newMatrix[0].length; i++) {
				for (int j = 0; j < newMatrix.length; j++) {
					newMatrix[j][i] = Math.pow(matrix[j][0], i);
				}
			}
			return newMatrix;
		}
		return null;
	}

	default boolean checkSizeMatrix(double[][] a, double[][] b) {
		if (a != null && b != null) {
			int a_columns = a[0].length;
			int b_rows = b.length;
			if (a_columns != b_rows) {
				System.out.println(
						"\t\t\t ERROR: number of columns in matrix a is not equal the number of row in matrix b\n see https://de.wikipedia.org/wiki/Matrizenmultiplikation");
			} else {
				return true;
			}
		} else {
			if (a == null) {
				System.out.println("\t\t\t ERROR: matrix a is null");
			}
			if (b == null) {
				System.out.println("\t\t\t ERROR: matrix b is null");
			}
		}
		return false;
	}

	default double[][] transpose(double[][] matrix) {
		if (matrix != null) {
			double[][] newTMatrix = new double[matrix[0].length][matrix.length];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					newTMatrix[j][i] = matrix[i][j];
				}
			}
			return newTMatrix;
		}
		return null;
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
			System.out.println("\t\t\t ERROR:");
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
		System.out.println("\t\t\t <---permute---->");
		printMatrix(matrix);
		System.out.println("\t\t\t with sigma");
		System.out.println(Arrays.toString(sigma));

		double[][] permutetMatrix = new double[matrix.length][matrix.length];
		for (int i = 0; i < sigma.length; i++) {
			double[] tmp = matrix[sigma[i]];
			permutetMatrix[i] = tmp;
		}
		System.out.println("\t\t\t result matrix");
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
		System.out.println("\t\t\t <---scale---->");
		printMatrix(a);
		System.out.println("\t\t\t with factor " + factor + " and row " + row);

		for (int i = 0; i < a[0].length; i++) {
			double tmp = a[row][i];
			tmp = tmp * factor;
			a[row][i] = tmp;
		}
		System.out.println("\t\t\t result matrix");
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
		System.out.println("\t\t\t <---eliminate--->");

		MatrixGenerator generator = new MatrixGenerator();
		double[][] identityMatrix = generator.getIdentityMatrix(e_l.length);
		// identityMatrix * factor * e_l * e_k
		System.out.println("\t\t\t identity matrix");
		printMatrix(identityMatrix);
		System.out.println("\t\t\t with factor " + c);
		System.out.println("\t\t\t with vector e_l");
		printMatrix(e_l);
		System.out.println("\t\t\t with vector e_k");
		printMatrix(e_k);

		double[][] lk = mult(e_l, e_k);
		double[][] lkc = multWithFactor(c, lk);
		double[][] lkcid = mult(identityMatrix, lkc);

		System.out.println("=");
		printMatrix(lkcid);
		return lkcid;
	}

	default double[][] addRows(int rowIndex1, int rowIndex2, double[][] matrix) {
		System.out.println("\t\t\t <---add rows in matrix---->");
		printMatrix(matrix);
		double[] row1 = matrix[rowIndex1];
		double[] row2 = matrix[rowIndex2];

		System.out.println(Arrays.toString(row1) + " + " + Arrays.toString(row2));
		for (int i = 0; i < row1.length; i++) {
			double tmp = row1[i] + row2[i];
			row1[i] = tmp;
		}
		System.out.println("\t\t\t result matrix");
		printMatrix(matrix);
		return matrix;
	}

	default double[][] addRows(int rowIndex1, int rowIndex2, double factor, double[][] matrix) {
		System.out.println("\t\t\t <---add rows in matrix---->");
		printMatrix(matrix);
		double[] row1 = matrix[rowIndex1];
		double[] tmpRow = new double[row1.length];

		System.out.println("\t\t\t row to scale with factor " + factor);
		System.out.println(Arrays.toString(row1));
		// scale row p with s
		for (int i = 0; i < row1.length; i++) {
			tmpRow[i] = row1[i] * factor;
		}

		double[] row2 = matrix[rowIndex2];
		System.out.println("\t\t\t row to add (first row is scaled)");
		System.out.println(Arrays.toString(tmpRow) + " + " + Arrays.toString(row2));
		for (int i = 0; i < row1.length; i++) {
			double tmp = tmpRow[i] + row2[i];
			row2[i] = tmp;
		}

		matrix[rowIndex2] = row2;
		System.out.println("\t\t\t result matrix");
		printMatrix(matrix);
		return matrix;
	}

	default double[][] subtractRows(int rowIndex1, int rowIndex2, double[][] matrix) {
		System.out.println("\t\t\t subtract rows in matrix");
		printMatrix(matrix);
		double[] row1 = matrix[rowIndex1];
		double[] row2 = matrix[rowIndex2];

		System.out.println(Arrays.toString(row1) + " - " + Arrays.toString(row2));
		for (int i = 0; i < row1.length; i++) {
			double tmp = row1[i] - row2[i];
			row2[i] = tmp;
		}
		System.out.println("\t\t\t result matrix");
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
	 * Divide with Factor
	 * 
	 * @param factor
	 * @param matrix
	 * @return
	 */
	default double[][] divideWithFactor(double factor, double[][] matrix) {
		// TODO validate input

		if (factor != 0.) {
			double[][] resultMatrix = new double[matrix.length][matrix.length];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					double tmp = matrix[i][j] / factor;
					resultMatrix[i][j] = tmp;
				}
			}
			return resultMatrix;
		} else {
			System.out.println("ERROR: divide with 0.");
		}
		return null;
	}
}