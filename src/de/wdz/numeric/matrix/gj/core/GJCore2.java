package de.wdz.numeric.matrix.gj.core;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class GJCore2 implements IMatrixDoubleOperations {

	private List<double[][]> matrixList;

	public GJCore2() {
		this.setMatrixList(new ArrayList<>());
	}

	public void forwardSubstitution(double[][] A, double[][] B) {
		// iterate over current pivot row p
		for (int p = 0; p < A.length; p++) {

			// pivot code here
//			int max = p;
//			for (int i = 0; i < B.length; i++) {
//				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
//					max = i;
//				}
//			}

			// permutate rows
//			int[] sigma = getSigma(max, p, B.length);
//			A = permute(sigma, A);
			// B = permute(sigma, B);

			// scale row p to make element at (p, p) equal one
			double s = 1. / A[p][p]; // s <- 1/u_pp
			A = scale(p, s, A); // Yp <- s * Yp

			for (int r = p + 1; r < A.length; r++) {// Eliminate from future
													// rows
				s = (-1.) * A[r][p];
				A = addRows(p, r, s, A);// scale row p by s and add to row r
			}
		}
	}

	public void backSubstitution(double[][] A, double[][] B) {
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

	// scalierung(einmal) -> eliminierung(for each row)

	private double[][] build_e_l_Matrix(int size, int index) {
		double[][] resultMatrix = new double[size][1];
		for (int i = 0; i < size; i++) {
			if (i == index) {
				resultMatrix[i][0] = 1;
			}
		}
		return resultMatrix;
	}

	private double[][] build_e_k_Matrix(int size, int index) {
		double[][] resultMatrix = new double[1][size];
		for (int i = 0; i < size; i++) {
			if (i == index) {
				resultMatrix[0][i] = 1;
			}
		}
		return resultMatrix;
	}

	public List<double[][]> getMatrixList() {
		return matrixList;
	}

	public void setMatrixList(List<double[][]> matrixList) {
		this.matrixList = matrixList;
	}
}