package de.wdz.numeric.matrix.gaussJordan.core;

import de.wdz.numeric.matrix.Matrix;

public class Main {

	public static void main(String[] args) {
		GaussJordanCore core = new GaussJordanCore();
		Matrix testMatrix = core.getTestMatrix7();
		core.run(testMatrix);
	}
}