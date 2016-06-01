package de.wdz.numeric.apache.cholesky;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import de.wdz.numeric.matrix.MatrixGenerator;

public class A_Cholesky {

	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();
		double[][] testMatrix = generator.getTestMatrixBookExample();
		RealMatrix a_Matrix = new BlockRealMatrix(testMatrix);
		CholeskyDecomposition choleskyDecomposition = new CholeskyDecomposition(a_Matrix);
		RealMatrix L = choleskyDecomposition.getL();
		RealMatrix LT = choleskyDecomposition.getLT();
	}
}