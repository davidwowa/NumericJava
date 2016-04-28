package de.wdz.numeric.matrix;

public class MatrixGenerator {

	/**
	 * Get identity matrix with diagonale with 1
	 */
	public double[][] getIdentityMatrix(int size) {
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

	public double[][] getRandomMatrix(int size) {
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

	public double[][] getRandomMatrix(int x, int y) {
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

	public double[][] getTestMatrix() {
		double[][] doubleMatrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix2() {
		double[][] doubleMatrix = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix3() {
		double[][] doubleMatrix = { { 3, 2, 1 }, { 1, 0, 2 } };
		return doubleMatrix;
	}

	/**
	 * Not for Gauss Jordan
	 * 
	 * @return
	 */
	public double[][] getTestMatrix4() {
		double[][] doubleMatrix = { { 1, 2 }, { 0, 1 }, { 4, 0 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix5() {
		double[][] doubleMatrix = { { 0, 0, 3 }, { 0, 5, 6 }, { 7, 8, 9 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix6() {
		double[][] doubleMatrix = { { 0, 0, 3 }, { 0, 0, 6 }, { 7, 8, 9 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix7() {
		double[][] doubleMatrix = { { 0, 1, 2, 1 }, { 0, 0, 1, 2 }, { 2, 1, 0, 0 }, { 1, 2, 1, 0 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix77() {
		double[][] doubleMatrix = { { 2, 1, 0, 0 }, { 0, 1, 2, 1 }, { 0, 0, 1, 2 }, { 1, 2, 1, 0 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix777() {
		double[][] doubleMatrix = { { 1, 2, 1, 0 }, { 2, 1, 0, 0 }, { 0, 1, 2, 1 }, { 0, 0, 1, 2 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix8() {
		double[][] doubleMatrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 1, 2, 3 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrix9() {
		double[][] doubleMatrix = { { 1, 1, -2 }, { 0, 1, -1 }, { 3, -1, 1 } };
		return doubleMatrix;
	}

	public double[][] getTestVector() {
		double[][] doubleMatrix = { { -3 }, { -1 }, { 4 } };
		return doubleMatrix;
	}

	public double[][] getTestVectorNotizen() {
		double[][] doubleMatrix = { { 1 }, { 2 }, { 4 }, { 8 } };
		return doubleMatrix;
	}

	// ---
	public double[][] getRVector() {
		double[][] doubleMatrix = { { 46 }, { 79 } };
		return doubleMatrix;
	}

	public double[][] getTestRMatrix() {
		double[][] doubleMatrix = { { 6, 2 }, { 9, 8 } };
		return doubleMatrix;
	}

	// ---
	public double[][] getRVector2() {
		double[][] doubleMatrix = { { 2149 }, { 4687 }, { 145 }, { 367 } };
		return doubleMatrix;
	}

	public double[][] getTestRMatrix2() {
		double[][] doubleMatrix = { { 1, 6, Math.pow(6, 2), Math.pow(6, 3), Math.pow(6, 4) },
				{ 1, 8, Math.pow(8, 2), Math.pow(8, 3), Math.pow(8, 4) },
				{ 1, 2, Math.pow(2, 2), Math.pow(2, 3), Math.pow(2, 4) },
				{ 1, 3, Math.pow(3, 2), Math.pow(3, 3), Math.pow(3, 4) } };
		return doubleMatrix;
	}
}