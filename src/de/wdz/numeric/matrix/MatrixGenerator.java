package de.wdz.numeric.matrix;

public class MatrixGenerator {

	/**
	 * Get vector with ones
	 */
	public double[][] getVectorWithOnes(int size) {
		double[][] identityMatrix = new double[size][1];
		for (int i = 0; i < size; i++) {
			identityMatrix[i][0] = 1;
		}
		return identityMatrix;
	}

	/**
	 * Get identity hilbert matrix
	 */
	public double[][] getHilbertMatrix(int size) {
		double[][] identityMatrix = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				double value = (i + 1) + (j + 1) - 1;
				identityMatrix[i][j] = 1 / value;
			}
		}
		return identityMatrix;
	}

	/**
	 * Get identity matrix with diagonale with 1
	 */
	public double[][] getIdentityMatrix(int size) {
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

	/**
	 * Get identity matrix with diagonale with -1
	 */
	public double[][] getIdentityNegativeMatrix(int size) {
		double[][] identityMatrix = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identityMatrix[i][j] = -1.;
				} else {
					identityMatrix[i][j] = 0.;
				}
			}
		}
		return identityMatrix;
	}

	public double[][] getLeastSquareTemplateMatrix(int x, int y) {
		double[][] identityMatrix = new double[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (i == 1) {
					identityMatrix[i][j] = 1.;
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

	public double[][] getTestMatrixCLUExample() {
		double[][] doubleMatrix = { { 2, 3 }, { 5, 4 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrixLUExampleWikipedia() {
		double[][] doubleMatrix = { { 4, 3 }, { 6, 3 } };
		return doubleMatrix;
	}

	/**
	 * http://www.dorn.org/uni/sls/kap08/h10_02se.htm
	 * 
	 * @return
	 */
	public double[][] getTestMatrixLUExample() {
		double[][] doubleMatrix = { { 6, -2, 0 }, { 9, -1, 1 }, { 3, 7, 5 } };
		return doubleMatrix;
	}

	/**
	 * http://de.mathworks.com/help/matlab/ref/lu.html
	 * 
	 * @return
	 */
	public double[][] getTestMatrixLUExampleMatlab() {
		double[][] doubleMatrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrixLUExampleWikipedia2() {
		double[][] doubleMatrix = { { 1, 2, 3 }, { 1, 1, 1 }, { 3, 3, 1 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrixBookExample() {
		double[][] doubleMatrix = { { 1, 1, -2 }, { 0, 1, -1 }, { 3, -1, 1 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrixPLUExample() {
		double[][] doubleMatrix = { { 0, 1, 0 }, { -8, 8, 1 }, { 2, -2, 0 } };
		return doubleMatrix;
	}

	public double[][] getTestMatrixPLUE2xample() {
		double[][] doubleMatrix = { { -8, 8, 1 }, { 0, 1, 0 }, { 2, -2, 0 } };
		return doubleMatrix;
	}

	public double[][] getTestVectorBookExample() {
		double[][] doubleMatrix = { { -3 }, { -1 }, { 4 } };
		return doubleMatrix;
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

	// ---
	public double[][] getMatrixSquares() {
		double[][] doubleMatrix = { { 0., 1.7 }, { 0.5, 2.8 }, { 1., 4.8 }, { 1.5, 6.5 }, { 2., 7.1 }, { 2.5, 8.3 },
				{ 3., 7.7 }, { 3.5, 9.1 }, { 4., 9. }, { 4.5, 8.3 }, { 5., 9.8 }, { 5.5, 9.1 }, { 6., 8.8 },
				{ 6.5, 8.9 }, { 7., 8.7 }, { 7.5, 7.7 }, { 8., 6.3 }, { 8.5, 5.1 }, { 9., 5. }, { 9.5, 4.1 },
				{ 10., 3.1 } };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresAA() {
		double[][] doubleMatrix = { { 2.5 }, { 8.5 }, };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresAAA() {
		double[][] doubleMatrix = { { 0.5 }, { 9.5 }, };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresAAAA() {
		double[][] doubleMatrix = { { 0. }, { 5. }, { 10. } };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresA() {
		double[][] doubleMatrix = { { 0. }, { 0.5 }, { 1. }, { 1.5 }, { 2. }, { 2.5 }, { 3. }, { 3.5 }, { 4. }, { 4.5 },
				{ 5. }, { 5.5 }, { 6. }, { 6.5 }, { 7. }, { 7.5 }, { 8. }, { 8.5 }, { 9. }, { 9.5 }, { 10. } };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresbbb() {
		double[][] doubleMatrix = { { 8.3 }, { 5.1 }, };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresbbbb() {
		double[][] doubleMatrix = { { 2.8 }, { 4.1 }, };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresbbbbb() {
		double[][] doubleMatrix = { { 1.7 }, { 9.8 }, { 3.1 } };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresb() {
		double[][] doubleMatrix = { { 1.7 }, { 2.8 }, { 4.8 }, { 6.5 }, { 7.1 }, { 8.3 }, { 7.7 }, { 9.1 }, { 9. },
				{ 8.3 }, { 9.8 }, { 9.1 }, { 8.8 }, { 8.9 }, { 8.7 }, { 7.7 }, { 6.3 }, { 5.1 }, { 5. }, { 4.1 },
				{ 3.1 } };
		return doubleMatrix;
	}

	public double[][] getMatrixSquaresbb() {
		double[][] doubleMatrix = { { 716.2500000000001 }, { 716.2500000000001 }, { 716.2500000000001 } };
		return doubleMatrix;
	}

	public double[][] getMatrixPolynomialRegressionBookExample() {
		double[][] doubleMatrix = { { 1, -1, 1 }, { 1, 0, 0 }, { 1, 2, 4 } };
		return doubleMatrix;
	}

	public double[][] getVectorPolynomialRegressionBookExample() {
		double[][] doubleMatrix = { { 1 }, { -1 }, { 7 } };
		return doubleMatrix;
	}

	public double[][] getMatrixLong() {
		double[][] doubleMatrix = { { 0, 1 }, { 1, 3 }, { 2, 4 }, { 3, 5 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 7, 8 },
				{ 8, 9 } };
		return doubleMatrix;
	}

	public double[][] getMatrixLongVector() {
		double[][] doubleMatrix = { { 1 }, { 3 }, { 4 }, { 5 }, { 5 }, { 6 }, { 7 }, { 8 }, { 9 } };
		return doubleMatrix;
	}

	public double[][] getMatrixLittle() {
		double[][] doubleMatrix = { { 0, 1 }, { 2, 2 }, { 1, 3 } };
		return doubleMatrix;
	}

	public double[][] getMatrixLittleVector() {
		double[][] doubleMatrix = { { 1 }, { 3 } };
		return doubleMatrix;
	}

	public double[][] getMatrixCholesky() {
		double[][] doubleMatrix = { { 2, -1, 0 }, { -1, 2, -1 }, { 0, -1, 2 } };
		return doubleMatrix;
	}
}