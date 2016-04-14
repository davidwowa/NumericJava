package de.wdz.numeric.matrix.gaussJordan.core;

import de.wdz.numeric.matrix.Matrix;

public class Main {

	public static void main(String[] args) {
		Matrix matrix = new Matrix(10);
		matrix.getIdentityMatrix(10);

		matrix.toString();
		for (int i = 0; i < 10; i++) {
			matrix.scale(i, i + 1, matrix);
		}
		System.out.println("--");
		matrix.toString();
	}
}