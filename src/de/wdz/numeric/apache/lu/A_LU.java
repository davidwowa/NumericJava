package de.wdz.numeric.apache.lu;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import de.wdz.numeric.matrix.MatrixGenerator;

public class A_LU {

	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();

		System.out.println("n\t\t\t\tresNorm\t\t\t\t\t\terrNorm");
		for (int n = 2; n < 12; n++) {

			double[][] xOnes = generator.getVectorWithOnes(n);
			double[][] hilbertMatrix = generator.getHilbertMatrix(n);

			RealMatrix H = new BlockRealMatrix(hilbertMatrix);
			RealMatrix x = new Array2DRowRealMatrix(xOnes);

			RealMatrix b = H.multiply(x);

			LUDecomposition decomposition = new LUDecomposition(H);

			RealMatrix x_hut = decomposition.getSolver().solve(b);

			// Residuum
			// RealMatrix residuum = b.subtract(H);

			// Error
			RealMatrix error = x_hut.subtract(x);

			// Infinity norm
			double residdumInfNorm = b.getNorm() - H.getNorm();
			double errorInfNorm = error.getNorm();

			System.out.println(n + "\t\t\t\t" + residdumInfNorm + "\t\t\t\t" + errorInfNorm);

		}
	}
}