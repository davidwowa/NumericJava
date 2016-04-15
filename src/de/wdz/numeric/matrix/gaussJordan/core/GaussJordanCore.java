package de.wdz.numeric.matrix.gaussJordan.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import de.wdz.numeric.matrix.Matrix;
import de.wdz.numeric.matrix.operation.IMatrixOperations;

public class GaussJordanCore implements IMatrixOperations {

	public void run(Matrix matrix) {
		permute(permutating(matrix), matrix);
		for (int i = 0; i < matrix.getMatrix().length; i++) {
		
		}
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

	private boolean containsIndex(int[] sigma, int index) {
		for (int i = 0; i < sigma.length; i++) {
			if (sigma[i] == index) {
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