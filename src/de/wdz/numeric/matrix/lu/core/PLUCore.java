package de.wdz.numeric.matrix.lu.core;

import java.util.ArrayList;
import java.util.List;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;
import de.wdz.numeric.matrix.operation.IMatrixDoubleOperations;

public class PLUCore implements IMatrixDoubleOperations {
	private MatrixGenerator generator;

	private List<double[][]> matrixList;
	private List<double[][]> inverses;
	private List<double[][]> permuations;

	private double[][] P;
	private double[][] L;
	private double[][] U;

	public PLUCore() {
		this.matrixList = new ArrayList<>();
		this.inverses = new ArrayList<>();
		this.permuations = new ArrayList<>();
		generator = new MatrixGenerator();
	}

	public void U(double[][] A) {
		L = new double[A.length][A[0].length];
		U = new double[A.length][A[0].length];
		P = new double[A.length][A[0].length];
		System.out.println("U - MATRIX");
		forwardSubstitutionP(A);
	}

	public void L() {
		System.out.println("L - MATRIX");
		GJCore core = new GJCore();
		// calculate inverse
		for (int i = 0; i < matrixList.size(); i++) {
			double[][] inverse = generator.getIdentityMatrix(matrixList.get(i).length);
			core.forwardSubstitution(matrixList.get(i), inverse);
			core.backwardSubstitution(matrixList.get(i), inverse);
			inverses.add(inverse);
		}
		// calculate L matrix
		// showAllMatrix();

		double[][] currentMatrix = inverses.get(0);
		for (int i = 1; i < inverses.size(); i++) {
			currentMatrix = mult(currentMatrix, inverses.get(i));
		}
		System.out.println("L - Matrix");
		printMatrix(currentMatrix);
		L = currentMatrix;
	}

	private void showAllMatrix() {
		System.out.println("matrix list");
		for (int i = 0; i < matrixList.size(); i++) {
			System.out.println("--");
			printMatrix(matrixList.get(i));
		}
		System.out.println("inverses list");
		for (int i = 0; i < inverses.size(); i++) {
			System.out.println("--");
			printMatrix(inverses.get(i));
		}
	}

	public void calculateP() {
		System.out.println("P - MATRIX");

		// showAllMatrix();
		if (permuations.size() != 0) {
			double[][] currentMatrix = permuations.get(0);
			for (int i = 1; i < permuations.size(); i++) {
				currentMatrix = mult(currentMatrix, permuations.get(i));
				System.out.println("P - Matrix");
				printMatrix(currentMatrix);
				P = currentMatrix;
			}
		} else {
			System.out.println("NO PERMUTATION MATRIX");
			P = generator.getIdentityMatrix(L.length);
		}
	}

	private void forwardSubstitutionP(double[][] A) {
		// iterate over current pivot row p
		for (int currentRow = 0; currentRow < A[0].length; currentRow++) {
			// pivot code here

			int max = currentRow;
			for (int i = 0; i < A.length; i++) {
				if (Math.abs(A[i][currentRow]) > Math.abs(A[max][currentRow])) {
					max = i;
				}
			}

			if (A[currentRow][currentRow] == 0.) {

				System.out.println("P-MATRIX-START");
				// permutate rows
				int[] sigma = getSigma(max, currentRow, A.length);
				A = permute(sigma, A);
				double[][] P = generator.getIdentityMatrix(A.length);
				P = permute(sigma, P);
				getPermuationsMatrix().add(P);
				System.out.println("P-MATRIX-END");
			}

			// scale row p to make element at (p, p) equal one
			double s = 1. / A[currentRow][currentRow]; // s <- 1/u_pp
			A = scale(currentRow, s, A); // Yp <- s * Yp

			double[][] scaledInverse = scale(currentRow, s, generator.getIdentityMatrix(A.length));
			getMatrixList().add(scaledInverse);

			for (int currentColumn = currentRow + 1; currentColumn < A.length; currentColumn++) {// Eliminate
																									// from
																									// future
				s = (-1.) * A[currentColumn][currentRow];
				A = addRows(currentRow, currentColumn, s, A);// scale
																// row p
																// by s
																// and
																// add
																// to
				double[][] e_k = build_e_k_Matrix(A.length, currentRow);
				double[][] e_l = build_e_l_Matrix(A.length, currentColumn);
				//
				double[][] elMatrix = eliminate_p(s, e_l, e_k);
				getMatrixList().add(elMatrix);
			}
		}
		U = A;
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

	private int[] getSigma(int newIndex, int oldIndex, int size) {
		int[] sigma = new int[size];
		for (int i = 0; i < sigma.length; i++) {
			sigma[i] = i;
		}
		sigma[newIndex] = oldIndex;
		sigma[oldIndex] = newIndex;
		return sigma;
	}

	public List<double[][]> getMatrixList() {
		return matrixList;
	}

	public void setMatrixList(List<double[][]> matrixList) {
		this.matrixList = matrixList;
	}

	public List<double[][]> getPermuationsMatrix() {
		return permuations;
	}

	public void setPermuationsMatrix(List<double[][]> permuationsMatrix) {
		this.permuations = permuationsMatrix;
	}

	public List<double[][]> getInverses() {
		return inverses;
	}

	public void setInverses(List<double[][]> inverses) {
		this.inverses = inverses;
	}

	public double[][] getP() {
		return P;
	}

	public void setP(double[][] p) {
		P = p;
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