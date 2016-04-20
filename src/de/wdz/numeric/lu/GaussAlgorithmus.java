package de.wdz.numeric.lu;

import java.util.Scanner;

/**
 * 
 * not from me
 */
public class GaussAlgorithmus {
	public void solve(double[][] A, double[] B) {

		int N = B.length;
		for (int k = 0; k < N; k++) {
			// find pivot row
			int max = k;
			for (int i = k + 1; i < N; i++)
				if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
					max = i;

			// swap row in A matrix
			double[] temp = A[k];
			A[k] = A[max];
			A[max] = temp;

			// swap corresponding values in constants matrix
			double t = B[k];
			B[k] = B[max];
			B[max] = t;

			// pivot within A and B
			for (int i = k + 1; i < N; i++) {
				double factor = A[i][k] / A[k][k];
				B[i] -= factor * B[k];
				for (int j = k; j < N; j++)
					A[i][j] -= factor * A[k][j];
			}
		}

		// back substitution
		double[] solution = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++)
				sum += A[i][j] * solution[j];
			solution[i] = (B[i] - sum) / A[i][i];
		}

		printSolution(solution);
	}

	// function to print solution
	public void printSolution(double[] sol) {
		int N = sol.length;
		System.out.println("\nSolution : ");
		for (int i = 0; i < N; i++)
			System.out.printf("%.3f ", sol[i]);
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		GaussAlgorithmus ge = new GaussAlgorithmus();

		System.out.println("Enter number of variables");
		int N = scan.nextInt();

		double[] B = new double[N];
		double[][] A = new double[N][N];

		System.out.println();
		System.out.println("Enter " + N + " equations coefficients ");
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				A[i][j] = scan.nextDouble();

		System.out.println();
		System.out.println("Enter " + N + " solutions");
		for (int i = 0; i < N; i++)
			B[i] = scan.nextDouble();

		scan.close();
		ge.solve(A, B);
	}
}