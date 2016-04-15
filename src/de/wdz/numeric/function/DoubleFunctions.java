package de.wdz.numeric.function;

public interface DoubleFunctions {

	default double pow(double value, int pow) {
		return Math.pow(value, pow);
	}

	default double fac(double limit) {
		double result = 1.;
		for (int i = 1; i <= limit; i++) {
			result = result * i;
		}
		return result;
	}

	default double sin(double x, int limit) {
		double result = 0.;
		boolean clock = true;
		for (int i = 0; i <= limit; i++) {
			if (i % 2 != 0) {
				double x_pow = pow(x, i);
				double divident = fac(i);
				double term = x_pow / divident;
				if (clock) {
					result = result + term;
					clock = false;
				} else {
					result = result - term;
					clock = true;
				}
			}
		}
		return result;
	}

	default double cos(double x, int limit) {
		double result = 0.;
		boolean clock = true;
		for (int i = 0; i <= limit; i++) {
			if (i % 2 == 0) {
				double x_pow = pow(x, i);
				double divident = fac(i);
				double term = x_pow / divident;
				if (clock) {
					result = result + term;
					clock = false;
				} else {
					result = result - term;
					clock = true;
				}
			}
		}
		return result;
	}

	default double exp(double x, int limit) {
		double result = 0.;
		for (int i = 0; i <= limit; i++) {
			double x_pow = pow(x, i);
			double divident = fac(i);
			double term = x_pow / divident;
			result = result + term;
		}
		return result;

	}

	default double tan(double x, int limit) {
		double sin = sin(x, limit);
		double cos = cos(x, limit);
		double result = sin / cos;
		return result;
	}

	default double sec(double x, int limit) {
		double cos = cos(x, limit);
		double result = 1. / cos;
		return result;
	}

	default double sec_2(double x, int limit) {
		double cos = cos(x, limit);
		double cos_2 = pow(cos, 2);
		double result = 1. / cos_2;
		return result;
	}

	// Newton Method
	default double sqrt(double x, int limit, int initialGuess) {
		double two = 2.;
		double x0 = initialGuess;
		for (int i = 0; i <= limit; i++) {
			double pow = pow(x0, 2);
			double fx0 = pow - x;
			double f_x0 = two * x0;
			double subtrahend = fx0 / f_x0;
			x0 = x0 - subtrahend;
		}
		return x0;
	}

	default double euler(double x, int limit) {
		double result = 0.;
		for (int i = 0; i <= limit; i++) {
			double fac = fac(i);
			result = result + (1. / fac);
		}
		return result;
	}
}