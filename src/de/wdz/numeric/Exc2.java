package de.wdz.numeric;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import Jama.LUDecomposition;
import Jama.Matrix;
import de.wdz.numeric.matrix.MatrixGenerator;

public class Exc2 {

	public static void main(String[] args) {
		int step = 100;
		int measurements = 3;
//		apache(step, measurements);
		jama(step, measurements);
	}

	public static void jama(int step, int measurements) {
		MatrixGenerator generator = new MatrixGenerator();

		for (int i = 1; i < 2000; i = i + 100) {
			long[] mes = new long[measurements];
			for (int j = 0; j < measurements; j++) {
				Matrix currentMatrix = new Matrix(generator.getRandomMatrix(i));
				long start = System.currentTimeMillis();
				new LUDecomposition(currentMatrix);
				long end = System.currentTimeMillis();
				mes[j] = end - start;
			}
			print(i, mes);
		}
	}

	public static void apache(int step, int measurements) {
		MatrixGenerator generator = new MatrixGenerator();

		for (int i = 1; i < 2000; i = i + step) {
			long[] mes = new long[measurements];
			for (int j = 0; j < measurements; j++) {
				RealMatrix currentMatrix = new Array2DRowRealMatrix(generator.getRandomMatrix(i));
				long start = System.currentTimeMillis();
				new org.apache.commons.math3.linear.LUDecomposition(currentMatrix);
				long end = System.currentTimeMillis();
				mes[j] = end - start;
			}
			print(i, mes);
		}
	}

	public static void print(int stepNr, long[] mes) {
		double sum = 0;
		for (int i = 0; i < mes.length; i++) {
			sum = sum + mes[i];
		}
		double result = sum / mes.length;
		System.out.println(stepNr - 1 + "," + result);
	}
}