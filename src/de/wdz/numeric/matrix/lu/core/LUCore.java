package de.wdz.numeric.matrix.lu.core;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class LUCore implements IMatrixDoubleOperations {
	private List<double[][]> matrixList;
	private MatrixGenerator generator;

	private double[][] L;
	private double[][] U;

	public LUCore() {
		this.matrixList = new ArrayList<>();
		generator = new MatrixGenerator();
	}

	public void U(double[][] A) {
		L = new double[A.length][A[0].length];
		U = new double[A.length][A[0].length];
		forwardSubstitution(A);
	}

	public void L() {

	}

	private void forwardSubstitution(double[][] A) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A.length; currentRow++) {
			// pivot code here

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

	public double[][] getL() {
		return L;
	}

	public void setL(double[][] l) {
		L = l;
	}

	public double[][] getU() {
		return U;
	}

	public void setU(double[][] u) {
		U = u;
	}
}