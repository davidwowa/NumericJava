package de.wdz.numeric.matrix.gj.core;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class GJCore3 implements IMatrixDoubleOperations {
	private List<double[][]> matrixList;
	private MatrixGenerator generator = new MatrixGenerator();

	public GJCore3() {
		this.matrixList = new ArrayList<>();
	}

	public void U(double[][] A) {
		// iterate over current pivot row p
		for (int p = 0; p < A.length; p++) {

			// pivot code here
			int max = p;
			for (int i = 0; i < A.length; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}

			// permutate rows
			int[] sigma = getSigma(max, p, A.length);
			A = permute(sigma, A);
			// B = permute(sigma, B);

			/**
			 * a) L- Kann S oder E sein - da S eine Diagonale Matrix ist und in
			 * dem Fall wo eine S mutltiplizerit wird bekommen wir dann wird die
			 * U angenähert
			 */

			/**
			 * b) (Erster Satz) Das ist schon von dem Algorithmus schon
			 * vorgegeben dass es so ist.
			 * <p>
			 * (Zweite Satz) Durch aufmultiplizieren von S und E wird es zu L
			 * qusi angenähert.
			 */

			/**
			 * c) Die ELimination schiebt ein Schritt weiter. ... i ... j => ...
			 * k ...
			 */

			/**
			 * d)
			 */
			// scale row p to make element at (p, p) equal one
			if (A[p][p] != 0.) {

				double s = 1. / A[p][p]; // s <- 1/u_pp
				A = scale(p, s, A); // Yp <- s * Yp

				double[][] sMatrix = generator.getIdentityMatrix(A.length);
				sMatrix = scale(p, s, sMatrix);
				getMatrixList().add(sMatrix);

				for (int r = p + 1; r < A.length; r++) {// Eliminate from future
					// if (A[r][p] != 0.) {
					s = (-1.) * A[r][p];
					A = addRows(p, r, s, A);// scale row p by s and add to
											// row r

					double[][] el = build_e_l_Matrix(A.length, r);
					double[][] ek = build_e_k_Matrix(A.length, p);
					double[][] elMatrix = eliminate(s, el, ek);
					getMatrixList().add(elMatrix);
				}
				// }
			}
		}
	}

	public void L() {
		List<double[][]> matrixListWithInverses = new ArrayList<>();
		if (getMatrixList() != null && !getMatrixList().isEmpty()) {
			for (double[][] currentMatrix : matrixList) {
				double[][] inverse = generator.getIdentityMatrix(currentMatrix.length);
				forwardSubstitution(currentMatrix, currentMatrix, inverse);
				backwardSubstitution(currentMatrix, currentMatrix, inverse);
				matrixListWithInverses.add(inverse);
			}
		} else {
			System.out.println("ERROR: \t\t\t matrix list is emtpy or null");
		}
		// TODO WDZ Hier passieren noch Fehler, forward oder back substitution
		// kommt eine 0-Matrix drin, diese verursacht die Fehler
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
			System.out.println("L");
			printMatrix(currentResult);
		} else {
			System.out.println("ERROR: \t\t\t list with inverses is emtpy");
		}
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
