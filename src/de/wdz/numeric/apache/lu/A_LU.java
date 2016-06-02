package de.wdz.numeric.apache.lu;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import de.wdz.numeric.matrix.MatrixGenerator;

public class A_LU {

	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();
		double[][] testMatrix = generator.getTestMatrixBookExample();
		RealMatrix a_Matrix = new BlockRealMatrix(testMatrix);
		LUDecomposition decomposition = new LUDecomposition(a_Matrix);
		RealMatrix L = decomposition.getL();
		RealMatrix U = decomposition.getU();
		RealMatrix P = decomposition.getP();

		System.out.println("L");
		System.out.println(L.toString());
		System.out.println("U");
		System.out.println(U.toString());
		System.out.println("P");
		System.out.println(P.toString());
	}
}