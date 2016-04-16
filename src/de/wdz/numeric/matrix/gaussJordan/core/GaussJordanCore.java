package de.wdz.numeric.matrix.gaussJordan.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import de.wdz.numeric.matrix.Matrix;
import de.wdz.numeric.matrix.operation.IMatrixOperations;

public class GaussJordanCore implements IMatrixOperations {

	// scalierung(einmal) -> eliminierung(for each row)
	public void run(Matrix matrix) {
		matrix = permute(permutating(matrix), matrix);

		for (int i = 0; i < matrix.getMatrix().length; i++) {
			matrix = permute(permutating(matrix), matrix);
			for (int j = 0; j < matrix.getMatrix()[0].length; j++) {
				if (i == j) {
					if (matrix.getMatrix()[i][j] != 0) {
						matrix = scale(i, 1. / matrix.getMatrix()[i][j], matrix);
					} else {
						// ??
						matrix = permute(permutating(matrix), matrix);
					}
				}

				for (int j2 = 1; j2 < matrix.getMatrix()[0].length; j2++) {
					// find first element != 0 in column

					double p = matrix.getMatrix()[j2][i];
					System.out.println("p = " + matrix.getMatrix()[j2][i]);
					if (p != 0) {
						System.out.println("matrix.getMatrix()[" + j2 + "][" + i + "] = " + p);
						// matrix = scale(j2, p, matrix);
						matrix = eliminate(p, build_e_l_Matrix(matrix.getMatrix().length, j2),
								build_e_k_Matrix(matrix.getMatrix().length, i));

					}
				}
			}
		}
	}

	private Matrix build_e_l_Matrix(int size, int index) {
		Matrix resultMatrix = new Matrix(size, 1);
		for (int i = 0; i < size; i++) {
			if (i == index) {
				resultMatrix.getMatrix()[i][0] = 1;
			}
		}
		return resultMatrix;
	}

	private Matrix build_e_k_Matrix(int size, int index) {
		Matrix resultMatrix = new Matrix(1, size);
		for (int i = 0; i < size; i++) {
			if (i == index) {
				resultMatrix.getMatrix()[0][i] = 1;
			}
		}
		return resultMatrix;
	}

	private int[] permutating(Matrix matrix) {
		int[] sigma = new int[matrix.getMatrix().length];
		for (int i = 0; i < matrix.getMatrix().length; i++) {
			double[] tmp = matrix.getMatrix()[i];
			int count = 0;
			boolean read = true;
			for (int j = 0; j < tmp.length; j++) {
				if (read) {
					if (tmp[j] == 0) {
						count++;
					} else {
						read = false;
					}
				}
			}
			sigma[i] = count;
		}

		int[] newSigma = new int[matrix.getMatrix().length];
		List<Integer> listt = Arrays.asList(ArrayUtils.toObject(sigma));
		ArrayList<Integer> list = new ArrayList<>(listt);
		int count = 0;
		while (list.size() > 0) {
			int index = list.indexOf(Collections.min(list));

			if (containsIndex(newSigma, index)) {
				addToSigma(newSigma, count, count);
			} else {
				addToSigma(newSigma, index, count);
			}
			list.remove(index);
			count++;
		}
		return newSigma;
	}

	private boolean containsIndex(int[] sigma, int value) {
		for (int i = 0; i < sigma.length; i++) {
			if (sigma[i] == value) {
				return true;
			}
		}
		return false;
	}

	private void addToSigma(int[] sigma, int value, int index) {
		if (!containsIndex(sigma, value)) {
			sigma[index] = value;
		}
	}
}