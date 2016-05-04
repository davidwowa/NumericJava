package de.wdz.numeric.matrix.gj.core;

import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class GJCore3 implements IMatrixDoubleOperations {

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
				inverse = permute(sigma, inverse);
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

	public void forwardSubstitution(double[][] A) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A.length; currentRow++) {
			// pivot code here
			int max = currentRow;
			int limit = A.length;
			System.out.println("LIMIT ----------------------------------" + limit);
			if (currentRow < A[0].length) {

				for (int i = 0; i < limit; i++) {
					System.out.println("I----------------------------------------" + i);
					System.out.println("CURRENT ROW--------------------------------------" + currentRow);
					System.out.println("MAX---------------------------" + max);
					if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
						max = i;
					}
				}

				if (A[currentRow][currentRow] == 0.) {

					// permutate rows
					int[] sigma = getSigma(max, currentRow, A.length);
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
																			// row
																			// p
																			// by
																			// s
																			// and
																			// add
																			// to
						}
					}
				}
			}
		}
	}

	public void permuteXS(double[][] A, double[][] B, double[][] inverse, int currentRow) {
		// pivot code here
		int max = currentRow;
		for (int i = 0; i < B.length; i++) {
			if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
				max = i;
			}
		}

		// permutate rows
		int[] sigma = getSigma(max, currentRow, B.length);
		permute(sigma, A);
		permute(sigma, B);
		permute(sigma, inverse);
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
				for (int r = p - 1; r >= 0; r--) {// Eliminate from future
					if (A[r][p] != 0.) {
						s = (-1.) * A[r][p];
						A = addRows(p, r, s, A);// scale row p by s and add to
						B = addRows(p, r, s, B); // row r
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