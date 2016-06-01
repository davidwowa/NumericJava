package de.wdz.numeric.apache.leastSquare;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class A_LeastSquare {
	public static void main(String[] args) {
		CurveFitting();
	}

	public static void LU() {
		// Create a real matrix with two rows and three columns, using a factory
		// method that selects the implementation class for us.
		double[][] matrixData = { { 1d, 2d, 3d }, { 2d, 5d, 3d } };
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);

		// One more with three rows, two columns, this time instantiating the
		// RealMatrix implementation class directly.
		double[][] matrixData2 = { { 1d, 2d }, { 2d, 5d }, { 1d, 7d } };
		RealMatrix n = new Array2DRowRealMatrix(matrixData2);

		// Note: The constructor copies the input double[][] array in both
		// cases.

		// Now multiply m by n
		RealMatrix p = m.multiply(n);
		System.out.println(p.getRowDimension()); // 2
		System.out.println(p.getColumnDimension()); // 2

		// Invert p, using LU decomposition
		RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();
	}

	public static void CurveFitting() {
		final WeightedObservedPoints obs = new WeightedObservedPoints();

		obs.add(0., 1.7);
		obs.add(0.5, 2.8);
		obs.add(1., 4.8);
		obs.add(1.5, 6.5);
		obs.add(2., 7.1);
		obs.add(2.5, 8.3);
		obs.add(3., 7.7);
		obs.add(3.5, 9.1);
		obs.add(4., 9.);
		obs.add(4.5, 8.3);
		obs.add(5., 9.8);
		obs.add(5.5, 9.1);
		obs.add(6., 8.8);
		obs.add(6.5, 8.9);
		obs.add(7., 8.7);
		obs.add(7.5, 7.7);
		obs.add(8., 6.3);
		obs.add(8.5, 5.1);
		obs.add(9., 5.);
		obs.add(9.5, 4.1);
		obs.add(10., 3.1);

		// Instantiate a third-degree polynomial fitter.
		final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2);

		// Retrieve fitted parameters (coefficients of the polynomial function).
		final double[] coeff = fitter.fit(obs.toList());
		System.out.println(ArrayUtils.toString(coeff));
	}
}