package de.wdz.numeric.apache.condition;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import de.wdz.numeric.matrix.MatrixGenerator;

public class A_CondNr {
	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();

		for (int n = 2; n < 12; n++) {

			double[][] hilbertMatrix = generator.getHilbertMatrix(n);

			RealMatrix H = new BlockRealMatrix(hilbertMatrix);

			SingularValueDecomposition decomposition = new SingularValueDecomposition(H);

			System.out.println(n + " " + decomposition.getConditionNumber());
		}
	}
}