package de.wdz.numeric.apache.cholesky;

import java.math.BigDecimal;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import Jama.Matrix;
import de.wdz.numeric.matrix.MatrixGenerator;

public class A_Cholesky {

	public static void main(String[] args) {
		MatrixGenerator generator = new MatrixGenerator();

		// positive definite matrix must be

		System.out.println(
				"n\tr\t\t\t\t\t\t\t" + "∆x" + "\t\t\t\t\t\t\tcondition\t\t\t\t\t\t\tequal numbers\t\t\t\t\t\t\terror");
		for (int n = 2; n < 10; n++) {

			double[][] xOnes = generator.getVectorWithOnes(n);
			double[][] hilbertMatrix = generator.getHilbertMatrix(n);

			RealMatrix H = new BlockRealMatrix(hilbertMatrix);
			RealMatrix x = new Array2DRowRealMatrix(xOnes);

			double conitionNumber = new SingularValueDecomposition(H).getConditionNumber();

			RealMatrix b = H.multiply(x);

			CholeskyDecomposition choleskyDecomposition = new CholeskyDecomposition(H);

			// Solution
			RealMatrix x_hut = choleskyDecomposition.getSolver().solve(b);

			// System.out.println(x_hut.toString());

			// Residuum
			RealMatrix residuum = H.multiply(x_hut).subtract(b);

			// Error
			RealMatrix error = x_hut.subtract(x);

			int numberOfOnes = equalVectorsToOne(x_hut.getData(), 2);

			Matrix jamaMatrixResiduum = new Matrix(residuum.getData());
			Matrix jamaMatrixError = new Matrix(error.getData());
			Matrix jamaMatrixX = new Matrix(xOnes);

			double normOfRsiduum = jamaMatrixResiduum.normInf();
			double normOfError = jamaMatrixError.normInf();

			Matrix jamaMatrixDeltaX = new Matrix(error.getData());

			double relativeErrorX = (jamaMatrixDeltaX.normInf()) / jamaMatrixX.normInf();

			System.out.println(n + "\t" + normOfRsiduum + "\t\t\t\t\t\t" + normOfError + "\t\t\t\t\t\t\t"
					+ conitionNumber + "\t\t\t\t\t\t\t" + numberOfOnes + "\t\t\t\t\t\t\t" + relativeErrorX);
		}
	}

	private static int equalVectorsToOne(double[][] vector, int scale) {
		int counter = 0;

		for (int i = 0; i < vector.length; i++) {
			String stringValue = vector[i][0] + "";

			String valueS = (String) stringValue.subSequence(0, scale);
			BigDecimal value = new BigDecimal(valueS);
			if (BigDecimal.ONE.equals(value)) {
				counter = counter + 1;
			}
		}
		return counter;
	}
}