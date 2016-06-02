package de.wdz.numeric.apache.condition;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import de.wdz.numeric.matrix.MatrixGenerator;
import de.wdz.numeric.matrix.gj.core.GJCore;

public class A_CondNr {
	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();
		double[][] testMatrix = generator.getTestMatrixBookExample();
		GJCore core = new GJCore();
		core.printMatrix(testMatrix);
		RealMatrix a_Matrix = new BlockRealMatrix(testMatrix);

		SingularValueDecomposition decomposition = new SingularValueDecomposition(a_Matrix);

		System.out.println(decomposition.getConditionNumber());
		System.out.println(a_Matrix.toString());
	}
}