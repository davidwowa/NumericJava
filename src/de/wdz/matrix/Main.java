package de.wdz.matrix;

public class Main implements IMatrixOperations {

	public static void main(String[] args) {
		Main main = new Main();
		Matrix matrix = main.getRandomMatrix(100);

		main.scale(2, 2., matrix);
		matrix.toString();
	}
}