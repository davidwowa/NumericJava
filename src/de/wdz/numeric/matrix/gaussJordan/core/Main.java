package de.wdz.numeric.matrix.gaussJordan.core;

import de.wdz.numeric.matrix.Matrix;

public class Main {

	public static void main(String[] args) {
		GaussJordanCore core = new GaussJordanCore();
		Matrix testMatrix = core.getTestMatrix5();
		core.run(testMatrix);

		// Main main = new Main();
		// Matrix matrix3 = main.getTestMatrix3();
		// Matrix matrix4 = main.getTestMatrix4();
		// Matrix result = main.mult(matrix3, matrix4);
		// result.toString();
		// System.out.println("--");
		// Matrix matrix1 = main.getTestMatrix();
		// Matrix matrix2 = main.getTestMatrix2();
		// Matrix result2 = main.mult(matrix1, matrix2);
		// result2.toString();

		// Matrix testMatrix = main.getTestMatrix();
		// testMatrix.toString();
		// System.out.println("--");
		// Matrix testMatrix2 = main.getTestMatrix2();
		// testMatrix2.toString();
		// System.out.println("--");
		// Matrix multMaptrix = main.mult(testMatrix, testMatrix2);
		// multMaptrix.toString();
		// int[] sigma = { 2, 1, 0 };
		// Matrix perumtatedMatr = main.permute(sigma, testMatrix);
		// Matrix scaledMatrix = main.scale(0, 3, testMatrix);
		// double[][] ela = { { 0 }, { 1 }, { 0 } };
		// double[][] elk = { { 1, 0, 0 } };
		// Matrix e_l = new Matrix(ela);
		// Matrix e_k = new Matrix(elk);
		// Matrix eliminated = main.eliminate(3, e_l, e_k);
		// eliminated.toString();
		//
		// Matrix identityMatrix = main.getIdentityMatrix(3);
		// identityMatrix.toString();
		// System.out.println("--");
		// Matrix scaledMatrix = main.multWithFactor(3, identityMatrix);
		// scaledMatrix.toString();
		// Matrix multWithFactor = main.multWithVector(scaledMatrix, e_l);
		// System.out.println("--");
		// multWithFactor.toString();
		// Matrix endMatr = main.multWithVector(multWithFactor, e_k);
		// System.out.println("--");
		// endMatr.toString();

	}
}