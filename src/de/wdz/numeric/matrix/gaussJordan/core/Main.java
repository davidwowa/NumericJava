package de.wdz.numeric.matrix.gaussJordan.core;

public class Main {

	public static void main(String[] args) {
		GJCore2 core = new GJCore2();

		double[][] A = core.getTestMatrix9();
		double[][] b = core.getTestVector();

		core.runGaussSimple(A, b);
	}
}