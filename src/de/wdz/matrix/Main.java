package de.wdz.matrix;

public class Main implements IMatrixOperations {

	public static void main(String[] args) {
		Main main = new Main();
		Matrix matrix = main.getIdentityMatrix(3);

		int[] sigma = new int[3];
		sigma[0] = 1;
		sigma[1] = 2;
		sigma[2] = 0;

		Matrix matrix2 = main.permute(sigma, matrix);
		matrix2.toString();
	}
}