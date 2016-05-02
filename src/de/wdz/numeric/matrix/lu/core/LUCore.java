package de.wdz.numeric.matrix.lu.core;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class LUCore implements IMatrixDoubleOperations {
	private List<double[][]> matrixList;
	private MatrixGenerator generator;

	public LUCore() {
		this.matrixList = new ArrayList<>();
		generator = new MatrixGenerator();
	}

	public void U(double[][] A) {
		forwardSubstitution(A);
	}

	public void L() {
		List<double[][]> matrixListWithInverses = new ArrayList<>();
		if (getMatrixList() != null && !getMatrixList().isEmpty()) {
			// loop :(
			for (int i = 0; i < getMatrixList().size(); i++) {
				double[][] inverse = generator.getIdentityMatrix(getMatrixList().get(i).length);
				forwardSubstitution(getMatrixList().get(i));
				backwardSubstitution(getMatrixList().get(i));
				matrixListWithInverses.add(inverse);
			}
		} else {
			System.out.println("ERROR: \t\t\t matrix list is emtpy or null");
		}
		if (!matrixListWithInverses.isEmpty()) {
			double[][] currentResult = matrixListWithInverses.get(matrixListWithInverses.size() - 1);
			for (int i = matrixListWithInverses.size() - 1; i > 1; i--) {
				System.out.println("\t\t\t mult matrix");
				printMatrix(currentResult);
				System.out.println("\t\t\t with matrix");
				printMatrix(matrixListWithInverses.get(i));
				currentResult = mult(currentResult, matrixListWithInverses.get(i));
				System.out.println("\t\t\t result");
				printMatrix(currentResult);
			}
			System.out.println("---L---");
			printMatrix(currentResult);
		} else {
			System.out.println("ERROR: \t\t\t list with inverses is emtpy");
		}
	}

	private void forwardSubstitution(double[][] A) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A.length; currentRow++) {
			// pivot code here

			// scale row p to make element at (p, p) equal one
			if (A[currentRow][currentRow] != 0.) {
				double s = 1. / A[currentRow][currentRow]; // s <- 1/u_pp
				A = scale(currentRow, s, A); // Yp <- s * Yp

				double[][] scaledInverse = scale(currentRow, s, generator.getIdentityMatrix(A.length));

				getMatrixList().add(scaledInverse);

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

						double[][] e_k = build_e_k_Matrix(A.length, currentRow);
						double[][] e_l = build_e_l_Matrix(A.length, currentColumn);

						double[][] elMatrix = eliminate(s, e_l, e_k);
						getMatrixList().add(elMatrix);
					}
				}
			}
		}
	}

	private void backwardSubstitution(double[][] A) {
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