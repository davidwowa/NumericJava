package de.wdz.numeric.apache.leastSquare;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import de.wdz.numeric.csv.CSVHandler;

public class A_LeastSquare {
	public static void main(String[] args) {
		CurveFitting();
	}

	public static void CurveFitting() {
		WeightedObservedPoints obs = new WeightedObservedPoints();

		CSVHandler csvHandler = new CSVHandler("/Users/David/Desktop/data.csv");

		double[][] matrix = csvHandler.read();

		for (int i = 0; i < matrix.length; i++) {
			obs.add(matrix[i][0], matrix[i][1]);
		}

		// Instantiate a third-degree polynomial fitter.
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2);

		// Retrieve fitted parameters (coefficients of the polynomial function).
		double[] coeff = fitter.fit(obs.toList());
		System.out.println(ArrayUtils.toString(coeff));
	}
}