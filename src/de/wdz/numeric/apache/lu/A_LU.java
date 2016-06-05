package de.wdz.numeric.apache.lu;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import Jama.Matrix;
import de.wdz.numeric.matrix.MatrixGenerator;

public class A_LU {

	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();

		// positive definite matrix must be

		System.out.println("n\tr\t\t\t\t\t\t\t" + "∆x" + "\t\t\t\t\t\t\tcondition\t\t\t\t\t\t\tequal numbers");
		for (int n = 2; n < 10; n++) {

			double[][] xOnes = generator.getVectorWithOnes(n);
			double[][] hilbertMatrix = generator.getHilbertMatrix(n);

			RealMatrix H = new BlockRealMatrix(hilbertMatrix);
			RealMatrix x = new Array2DRowRealMatrix(xOnes);

			double conitionNumber = new SingularValueDecomposition(H).getConditionNumber();

			RealMatrix b = H.multiply(x);

			LUDecomposition decomposition = new LUDecomposition(H);

			// Solution
			RealMatrix x_hut = decomposition.getSolver().solve(b);

			// System.out.println(x_hut.toString());

			// Residuum
			RealMatrix residuum = H.multiply(x_hut).subtract(b);

			// Error
			RealMatrix error = x_hut.subtract(x);

			int numberOfOnes = equalVectorsToOne(x_hut.getData());

			// Infinity norm
			// double residdumInfNorm = residuum.getNorm();
			// double errorInfNorm = error.getNorm();

			// for example use the b-vector inf norm
			Matrix jamaMatrixResiduum = new Matrix(residuum.getData());
			Matrix jamaMatrixError = new Matrix(error.getData());

			System.out.println(n + "\t" + jamaMatrixResiduum.normInf() + "\t\t\t\t\t\t" + jamaMatrixError.normInf()
					+ "\t\t\t\t\t\t\t" + conitionNumber + "\t\t\t\t\t\t\t" + numberOfOnes);
		}
	}

	private static int equalVectorsToOne(double[][] vector) {
		int counter = 0;

		// GJCore core = new GJCore();
		// core.printMatrix(vector);

		for (int i = 0; i < vector.length; i++) {
			if (vector[i][0] == 1.d) {
				counter = counter++;
			}
		}

		return counter;
	}
}