package de.wdz.numeric.matrix.gj.core;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class GJCore implements IMatrixDoubleOperations {

	public void inverse(double[][] A) {
		MatrixGenerator generator = new MatrixGenerator();
		double[][] inverse = generator.getIdentityMatrix(A.length);

		forwardSubstitution(A, inverse);
		backwardSubstitution(A, inverse);
	}

	public void forwardSubstitution(double[][] A, double[][] B, double[][] inverse) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A.length; currentRow++) {
			// pivot code here
			int max = currentRow;
			for (int i = 0; i < B.length; i++) {
				if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
					max = i;
				}
			}

			if (A[currentRow][currentRow] == 0.) {

				// permutate rows
				int[] sigma = getSigma(max, currentRow, B.length);
				A = permute(sigma, A);
				B = permute(sigma, B);
				System.out.println("INVERSE-START");
				inverse = permute(sigma, inverse);
				System.out.println("INVERSE-END");
			}

			// scale row p to make element at (p, p) equal one
			if (A[currentRow][currentRow] != 0.) {
				double s = 1. / A[currentRow][currentRow]; // s <- 1/u_pp
				A = scale(currentRow, s, A); // Yp <- s * Yp
				B = scale(currentRow, s, B);
				System.out.println("INVERSE-START");
				inverse = scale(currentRow, s, inverse);
				System.out.println("INVERSE_END");
				for (int currentColumn = currentRow + 1; currentColumn < A.length; currentColumn++) {// Eliminate
																										// from
																										// future
					if (A[currentColumn][currentRow] != 0.) {
						s = (-1.) * A[currentColumn][currentRow];
						A = addRows(currentRow, currentColumn, s, A);// scale
																		// row p
																		// by s
																		// and
																		// add
																		// to
						B = addRows(currentRow, currentColumn, s, B); // row r
						System.out.println("INVERSE_START");
						inverse = addRows(currentRow, currentColumn, s, inverse);
						System.out.println("INVERSE_END");
					}
				}
			}
		}
	}

	public void forwardSubstitutionB(double[][] A, double[][] B) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A.length; currentRow++) {
			// pivot code here
			int max = currentRow;
			for (int i = 0; i < B.length; i++) {
				if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
					max = i;
				}
			}

			if (A[currentRow][currentRow] == 0.) {

				// permutate rows
				int[] sigma = getSigma(max, currentRow, B.length);
				A = permute(sigma, A);
				B = permute(sigma, B);
			}

			// scale row p to make element at (p, p) equal one
			if (A[currentRow][currentRow] != 0.) {
				double s = 1. / A[currentRow][currentRow]; // s <- 1/u_pp
				A = scale(currentRow, s, A); // Yp <- s * Yp
				B = scale(currentRow, s, B);
				for (int currentColumn = currentRow + 1; currentColumn < A.length; currentColumn++) {// Eliminate
																										// from
																										// future
					if (A[currentColumn][currentRow] != 0.) {
						s = (-1.) * A[currentColumn][currentRow];
						A = addRows(currentRow, currentColumn, s, A);// scale
																		// row p
																		// by s
																		// and
																		// add
																		// to
						B = addRows(currentRow, currentColumn, s, B); // row r
					}
				}
			}
		}
	}

	public void forwardSubstitution(double[][] A, double[][] inverse) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A[0].length; currentRow++) {
			// pivot code here
			int max = currentRow;
			for (int i = 0; i < A[0].length; i++) {
				if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
					max = i;
				}
			}

			if (A[currentRow][currentRow] == 0.) {

				// permutate rows
				int[] sigma = getSigma(max, currentRow, A[0].length);
				A = permute(sigma, A);
				inverse = permute(sigma, inverse);
			}

			// scale row p to make element at (p, p) equal one
			if (A[currentRow][currentRow] != 0.) {
				double s = 1. / A[currentRow][currentRow]; // s <- 1/u_pp
				A = scale(currentRow, s, A); // Yp <- s * Yp
				inverse = scale(currentRow, s, inverse);
				for (int currentColumn = currentRow + 1; currentColumn < A.length; currentColumn++) {// Eliminate
																										// from
																										// future
					if (A[currentColumn][currentRow] != 0.) {
						s = (-1.) * A[currentColumn][currentRow];
						A = addRows(currentRow, currentColumn, s, A);// scale
																		// row p
																		// by s
																		// and
																		// add
																		// to
						inverse = addRows(currentRow, currentColumn, s, inverse);
					}
				}
			}
		}
	}

	public void forwardSubstitution(double[][] A) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A[0].length; currentRow++) {
			// pivot code here
			int max = currentRow;
			for (int i = 0; i < A[0].length; i++) {
				if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
					max = i;
				}
			}

			System.out.println("A[" + currentRow + "][" + currentRow + "] == 0. -> " + A[currentRow][currentRow]);
			if (A[currentRow][currentRow] == 0.) {
				// permutate rows
				int[] sigma = getSigma(max, currentRow, A[0].length);
				A = permute(sigma, A);
			}

			// scale row p to make element at (p, p) equal one
			if (A[currentRow][currentRow] != 0.) {
				double s = 1. / A[currentRow][currentRow]; // s <- 1/u_pp
				A = scale(currentRow, s, A); // Yp <- s * Yp
				for (int currentColumn = currentRow + 1; currentColumn < A.length; currentColumn++) {// Eliminate
																										// from
																										// future
					if (A[currentColumn][currentRow] != 0.) {
						s = (-1.) * A[currentColumn][currentRow];
						A = addRows(currentRow, currentColumn, s, A);// scale
																		// row p
																		// by s
																		// and
																		// add
																		// to
					}
				}
			}
		}
	}

	public void backwardSubstitution(double[][] A, double[][] B, double[][] inverse) {
		// iterate over current pivot row p
		for (int p = A.length - 1; p >= 0; p--) {
			// permuteXS(A, B, inverse, p);

			// scale row p to make element at (p, p) equal one
			if (A[p][p] != 0.) {
				double s = 1. / A[p][p]; // s <- 1/u_pp
				A = scale(p, s, A); // Yp <- s * Yp
				B = scale(p, s, B);
				System.out.println("START_INVERSE");
				inverse = scale(p, s, inverse);
				System.out.println("END_INVERSE");
				for (int r = p - 1; r >= 0; r--) {// Eliminate from future
					if (A[r][p] != 0.) {
						s = (-1.) * A[r][p];
						A = addRows(p, r, s, A);// scale row p by s and add to
						B = addRows(p, r, s, B); // row r
						System.out.println("START_INVERSE");
						inverse = addRows(p, r, s, inverse);
						System.out.println("END_INVERSE");
					}
				}
			}
		}
	}

	public void backwardSubstitution(double[][] A, double[][] inverse) {
		// iterate over current pivot row p
		for (int p = A.length - 1; p >= 0; p--) {
			// permuteXS(A, B, inverse, p);

			// scale row p to make element at (p, p) equal one
			if (A[p][p] != 0.) {
				double s = 1. / A[p][p]; // s <- 1/u_pp
				A = scale(p, s, A); // Yp <- s * Yp
				inverse = scale(p, s, inverse);
				for (int r = p - 1; r >= 0; r--) {// Eliminate from future
					if (A[r][p] != 0.) {
						s = (-1.) * A[r][p];
						A = addRows(p, r, s, A);// scale row p by s and add to
						inverse = addRows(p, r, s, inverse);
					}
				}
			}
		}
	}

	public void backwardSubstitution(double[][] A) {
		// iterate over current pivot row p
		for (int p = A.length - 1; p >= 0; p--) {
			// permuteXS(A, B, inverse, p);

			// scale row p to make element at (p, p) equal one
			if (A[p][p] != 0.) {
				double s = 1. / A[p][p]; // s <- 1/u_pp
				A = scale(p, s, A); // Yp <- s * Yp
				for (int r = p - 1; r >= 0; r--) {// Eliminate from future
					if (A[r][p] != 0.) {
						s = (-1.) * A[r][p];
						A = addRows(p, r, s, A);// scale row p by s and add to
					}
				}
			}
		}
	}

	public double[][] adjustMatrix(double[][] A) {
		if (A.length != A[0].length) {
			MatrixGenerator generator = new MatrixGenerator();
			double[][] B = generator.getIdentityNegativeMatrix(A.length);
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A[0].length; j++) {
					System.out.println("B[" + i + "][" + j + "] = A[" + i + "][" + j + "] = " + A[i][j]);
					B[i][j] = A[i][j];
				}
			}
			return B;
		}
		return null;
	}

	private int[] getSigma(int newIndex, int oldIndex, int size) {
		int[] sigma = new int[size];
		for (int i = 0; i < sigma.length; i++) {
			sigma[i] = i;
		}
		sigma[newIndex] = oldIndex;
		sigma[oldIndex] = newIndex;
		return sigma;
	}
}