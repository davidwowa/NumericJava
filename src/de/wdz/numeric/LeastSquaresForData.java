package de.wdz.numeric;

import de.wdz.numeric.csv.CSVHandler;
import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;

public class LeastSquaresForData {

	public static void main(String[] args) {
		
		// fx_result = @(x) -0.2805016493803706*x^2 + 2.8400814288686433*x + 2.140542066628967;
		// von unten nach oben
		// APACHE
		// f_apache = @(x) 0.010639152685035536*x^2 - 10.496754906771143*x + 1896.6352591208715;
		// Jama
		// f_jama = @(x) 9.836948239164106E-4*x^2 - 0.7590639992905102*x + 134.84914730392381;
		// Matlab
		// f_matlab = @(x) 2.2859314185814158E-8*x^2 - 8.933348101898063E-6*x + 0.0016025072727272648;
		// MatlabGPU
		// f_matlab = @(x) 1.0096618381618518E-7*x^2 + 7.315969030967048E-6*x + 0.023519818181818718;

		CSVHandler csv = new CSVHandler("/Users/David/git/NumericJava/matlab/Functions/measurementMatlabGPU.csv");
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
		double[][] rMatrix = core.rebuildMatrixToFormForLeastSquares(A, 3);
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