package de.wdz.numeric.matrix;

public class LUMatrix {

	private double[][] l;
	private double[][] u;
	private double[][] sourceMatrix;

	public LUMatrix(int size) {
		sourceMatrix = new double[size][size];
	}

	public LUMatrix(int x, int y) {
		sourceMatrix = new double[x][y];
	}

	public LUMatrix(double[][] sourceMatrix) {
		this.sourceMatrix = sourceMatrix;
	}

	public double[][] getL() {
		return l;
	}

	public void setL(double[][] l) {
		this.l = l;
	}

	public double[][] getU() {
		return u;
	}

	public void setU(double[][] u) {
		this.u = u;
	}

	public double[][] getSourceMatrix() {
		return sourceMatrix;
	}

	public void setSourceMatrix(double[][] sourceMatrix) {
		this.sourceMatrix = sourceMatrix;
	}

	public String sourceMatrixToString() {
		if (sourceMatrix != null) {
			for (int i = 0; i < sourceMatrix.length; i++) {
				for (int j = 0; j < sourceMatrix[0].length; j++) {
					System.out.print(sourceMatrix[i][j] + " ");
				}
				System.out.print(";\n");
			}
			return super.toString();
		} else {
			System.out.println("ERROR: create prior a matrix");
		}
		return null;
	}

	public String LMatrixToString() {
		if (l != null) {
			for (int i = 0; i < l.length; i++) {
				for (int j = 0; j < l[0].length; j++) {
					System.out.print(l[i][j] + " ");
				}
				System.out.print(";\n");
			}
			return super.toString();
		} else {
			System.out.println("ERROR: create prior a matrix");
		}
		return null;
	}

	public String UMatrixToString() {
		if (u != null) {
			for (int i = 0; i < u.length; i++) {
				for (int j = 0; j < u[0].length; j++) {
					System.out.print(u[i][j] + " ");
				}
				System.out.print(";\n");
			}
			return super.toString();
		} else {
			System.out.println("ERROR: create prior a matrix");
		}
		return null;
	}
}