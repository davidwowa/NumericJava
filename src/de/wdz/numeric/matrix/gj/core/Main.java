package de.wdz.numeric.matrix.gj.core;

public class Main {

	public static void main(String[] args) {
		GJCore2 core = new GJCore2();

		double[][] A = core.getTestMatrix9();
		core.printMatrix(A);
		double[][] b = core.getTestVector();
		core.printMatrix(b);
		core.forwardSubstitution(A, b);
	}
}