package de.wdz.matrix;

public class Main {

	public static void main(String[] args) {
		Matrix identityMatrix = new Matrix();
		identityMatrix = identityMatrix.getIdentityMatrix(10);

		Matrix testMatrix = identityMatrix.getTestMatrix();
		Matrix testVector = identityMatrix.getTestVector();

		testMatrix.toString();
		System.out.println("--");
		testVector.toString();

		Matrix resultMatrix = identityMatrix.multWithVector(testMatrix, testVector);
		resultMatrix.toString();
	}
}