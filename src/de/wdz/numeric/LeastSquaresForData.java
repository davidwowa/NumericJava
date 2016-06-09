package de.wdz.numeric;

import de.wdz.numeric.csv.CSVHandler;
import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;

public class LeastSquaresForData {

	public static void main(String[] args) {
		
		// f_apache = @(x) 9.341546267629971E-6*x^3 - 0.011253745270038168*x^2 + 3.621485208118603*x - 236.30065359482387;
		// f_jama = @(x) 2.873523678476959E-7*x^3 + 1.572970416237519E-4*x^2 - 0.12664244352719234*x + 24.95588235295793;

		CSVHandler csv = new CSVHandler("/Users/David/git/NumericJava/matlab/Functions/measurementApache.csv");
		double[][] data = csv.read();
		go(data);
	}

	public static void go(double[][] data) {
		GJCore core = new GJCore();
		MatrixGenerator generator = new MatrixGenerator();
		System.out.println("LEAST SQUARE EXERCISE");

		System.out.println("MATRIX A");
		double[][] A = new double[data.length][1];
		for (int i = 0; i < data.length; i++) {
			A[i][0] = data[i][0];
		}
		core.printMatrix(A);

		System.out.println("MATRIX b");
		double[][] b = new double[data.length][1];
		for (int i = 0; i < data.length; i++) {
			b[i][0] = data[i][1];
		}
		core.printMatrix(b);

		// double[][] At = core.transpose(A);
		// System.out.println("MATRIX At");
		// core.printMatrix(At);

		System.out.println("BUILD NEW A MATRIX");
		double[][] rMatrix = core.rebuildMatrixToFormForLeastSquares(A, 4);
		core.printMatrix(rMatrix);

		System.out.println("BUILD NEW At MATRIX");
		double[][] rAtMatrix = core.transpose(rMatrix);
		core.printMatrix(rAtMatrix);

		System.out.println("At * A");
		double[][] AtTimesA = core.mult(rAtMatrix, rMatrix);
		core.printMatrix(AtTimesA);

		System.out.println("At * b");
		double[][] newb = core.mult(rAtMatrix, b);

		core.printMatrix(newb);

		double[][] inverse = generator.getIdentityMatrix(AtTimesA.length);

		core.forwardSubstitution(AtTimesA, newb, inverse);
		core.backwardSubstitution(AtTimesA, newb, inverse);

		System.out.println("show result vector next top lines");
		System.out.println("RESULT");
		
		core.printMatrix(newb);
	}
}