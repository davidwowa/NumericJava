package de.wdz.numeric.matrix.gj.core;

import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

/**
 * 
 * @author David
 *
 */
public class GJCore2 implements IMatrixDoubleOperations {

	public void forwardSubstitution(double[][] A, double[][] B, double[][] inverse) {
		// iterate over current pivot row p
		for (int p = 0; p < A.length; p++) {

			// pivot code here
			int max = p;
			for (int i = 0; i < B.length; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}

			// permutate rows
			int[] sigma = getSigma(max, p, B.length);
			A = permute(sigma, A);
			B = permute(sigma, B);

			// scale row p to make element at (p, p) equal one
			if (A[p][p] != 0.) {
				double s = 1. / A[p][p]; // s <- 1/u_pp
				A = scale(p, s, A); // Yp <- s * Yp
				B = scale(p, s, B);
				inverse = scale(p, s, inverse);
				for (int r = p + 1; r < A.length; r++) {// Eliminate from future
					if (A[r][p] != 0.) {
						s = (-1.) * A[r][p];
						A = addRows(p, r, s, A);// scale row p by s and add to
												// row r
						B = addRows(p, r, s, B);
						inverse = addRows(p, r, s, inverse);
					}
				}
			}
		}
	}

	public void backwardSubstitution(double[][] A, double[][] B, double[][] inverse) {
		// iterate over current pivot row p
		for (int p = A.length - 1; p >= 0; p--) {

			// pivot code here
			int max = p;
			for (int i = 0; i < B.length; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}

			// permutate rows
			int[] sigma = getSigma(max, p, B.length);
			A = permute(sigma, A);
			B = permute(sigma, B);

			// scale row p to make element at (p, p) equal one
			if (A[p][p] != 0.) {

				double s = 1. / A[p][p]; // s <- 1/u_pp
				A = scale(p, s, A); // Yp <- s * Yp
				B = scale(p, s, B);
				inverse = scale(p, s, inverse);
				for (int r = p - 1; r < A.length - 1 && r >= 0; r--) {// Eliminate
																		// from
																		// future
					if (A[r][p] != 0.) {
						s = (-1.) * A[r][p];
						A = addRows(p, r, s, A);// scale row p by s and add to
												// row r
						B = addRows(p, r, s, B);
						inverse = addRows(p, r, s, inverse);
					}
				}
			}
		}
	}

	public void runGaussSimple(double[][] A, double[][] B) {
		int N = B.length;
		for (int k = 0; k < N; k++) {
			// find pivot row
			int max = k;
			for (int i = k + 1; i < N; i++)
				if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
					max = i;

			// swap row in A matrix
			double[] temp = A[k];
			A[k] = A[max];
			A[max] = temp;

			// swap corresponding values in constants matrix
			double t = B[k][0];
			B[k][0] = B[max][0];
			B[max][0] = t;

			// pivot within A and B
			for (int i = k + 1; i < N; i++) {
				double factor = A[i][k] / A[k][k];
				B[i][0] -= factor * B[k][0];
				for (int j = k; j < N; j++)
					A[i][j] -= factor * A[k][j];
			}
		}

		System.out.println("--");
		printMatrix(A);
		System.out.println("--");
		// back substitution
		double[] solution = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++)
				sum += A[i][j] * solution[j];
			solution[i] = (B[i][0] - sum) / A[i][i];
		}
		System.out.println("solution");
		printMatrix(solution);
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