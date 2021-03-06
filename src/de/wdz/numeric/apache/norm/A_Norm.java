package de.wdz.numeric.apache.norm;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;

public class A_Norm {
	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();
		double[][] testMatrix = generator.getTestMatrixBookExample();
		RealMatrix a_Matrix = new BlockRealMatrix(testMatrix);

		GJCore core = new GJCore();

		core.printMatrix(testMatrix);

		System.out.println(a_Matrix.getNorm());

		System.out.println(a_Matrix.getFrobeniusNorm());
	}
}