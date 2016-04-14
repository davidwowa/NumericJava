package de.wdz.matrix;

public class Main {

	public static void main(String[] args) {
		Matrix identityMatrix = new Matrix();
		Matrix testMatrix = identityMatrix.getTestMatrix();
		int[] sigma = { 0, 2, 1 };
		testMatrix.permute(sigma, testMatrix);
	}
}