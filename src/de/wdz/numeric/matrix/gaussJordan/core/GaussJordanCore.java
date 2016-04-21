package de.wdz.numeric.matrix.gaussJordan.core;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.Matrix;
import de.wdz.numeric.matrix.operation.IMatrixOperations;

public class GaussJordanCore implements IMatrixOperations {

	private List<Matrix> matrixList;

	public GaussJordanCore() {
		this.setMatrixList(new ArrayList<>());
	}

	public void forwardSubstitution(Matrix a, Matrix b) {
	}

	public void backSubstitution(Matrix a, Matrix b) {
	}

	public void runGaussSimple(Matrix a, Matrix b) {
		double[][] A = a.getMatrix();
		double[][] B = b.getMatrix();

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
		a.toString();
		System.out.println("--");
		// back substitution
		double[] solution = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++)
				sum += A[i][j] * solution[j];
			solution[i] = (B[i][0] - sum) / A[i][i];
		}

		Matrix sol = new Matrix(solution);
		sol.toString();
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

	private Matrix build_e_l_Matrix(int size, int index) {
		Matrix resultMatrix = new Matrix(size, 1);
		for (int i = 0; i < size; i++) {
			if (i == index) {
				resultMatrix.getMatrix()[i][0] = 1;
			}
		}
		return resultMatrix;
	}

	private Matrix build_e_k_Matrix(int size, int index) {
		Matrix resultMatrix = new Matrix(1, size);
		for (int i = 0; i < size; i++) {
			if (i == index) {
				resultMatrix.getMatrix()[0][i] = 1;
			}
		}
		return resultMatrix;
	}

	public List<Matrix> getMatrixList() {
		return matrixList;
	}

	public void setMatrixList(List<Matrix> matrixList) {
		this.matrixList = matrixList;
	}
}