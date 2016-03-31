package de.wdz.functions;

import java.math.BigDecimal;
import java.math.MathContext;

public interface BigDecimalFunctions {

	default BigDecimal pow(BigDecimal value, int pow) {
		return value.pow(pow, MathContext.DECIMAL128);
	}

	default BigDecimal fac(BigDecimal value) {
		int limit = value.intValue();
		BigDecimal result = BigDecimal.ONE;
		for (int i = 1; i <= limit; i++) {
			result = result.multiply(new BigDecimal(i), MathContext.DECIMAL128);
		}
		return result;
	}

	default BigDecimal sin(BigDecimal x, int limit) {
		BigDecimal result = new BigDecimal("0", MathContext.DECIMAL128);
		boolean clock = true;
		for (int i = 0; i <= limit; i++) {
			if (i % 2 != 0) {
				BigDecimal x_pow = pow(x, i);
				BigDecimal divident = fac(new BigDecimal(i, MathContext.DECIMAL128));
				BigDecimal term = x_pow.divide(divident, MathContext.DECIMAL128);
				if (clock) {
					result = result.add(term, MathContext.DECIMAL128);
					clock = false;
				} else {
					result = result.subtract(term, MathContext.DECIMAL128);
					clock = true;
				}
			}
		}
		return result;
	}

	default BigDecimal cos(BigDecimal x, int limit) {
		BigDecimal result = new BigDecimal("0", MathContext.DECIMAL128);
		boolean clock = true;
		for (int i = 0; i <= limit; i++) {
			if (i % 2 == 0) {
				BigDecimal x_pow = pow(x, i);
				BigDecimal divident = fac(new BigDecimal(i, MathContext.DECIMAL128));
				BigDecimal term = x_pow.divide(divident, MathContext.DECIMAL128);
				if (clock) {
					result = result.add(term, MathContext.DECIMAL128);
					clock = false;
				} else {
					result = result.subtract(term, MathContext.DECIMAL128);
					clock = true;
				}
			}
		}
		return result;
	}

	default BigDecimal exp(BigDecimal x, int limit) {
		BigDecimal result = new BigDecimal("0", MathContext.DECIMAL128);
		for (int i = 0; i <= limit; i++) {
			BigDecimal x_pow = pow(x, i);
			BigDecimal divident = fac(new BigDecimal(i, MathContext.DECIMAL128));
			BigDecimal term = x_pow.divide(divident, MathContext.DECIMAL128);
			result = result.add(term, MathContext.DECIMAL128);
		}
		return result;

	}

	default BigDecimal tan(BigDecimal x, int limit) {
		BigDecimal sin = sin(x, limit);
		BigDecimal cos = cos(x, limit);
		BigDecimal result = sin.divide(cos, MathContext.DECIMAL128);
		return result;
	}

	default BigDecimal sec(BigDecimal x, int limit) {
		BigDecimal cos = cos(x, limit);
		BigDecimal result = BigDecimal.ONE.divide(cos, MathContext.DECIMAL128);
		return result;
	}

	default BigDecimal sec_2(BigDecimal x, int limit) {
		BigDecimal cos = cos(x, limit);
		BigDecimal cos_2 = pow(cos, 2);
		BigDecimal result = BigDecimal.ONE.divide(cos_2, MathContext.DECIMAL128);
		return result;
	}

	/**
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-
	 *      BigDecimal
	 */
	default BigDecimal sqrtNewtonRaphson(BigDecimal c, BigDecimal xn, BigDecimal precision) {
		BigDecimal fx = xn.pow(2).add(c.negate());
		BigDecimal fpx = xn.multiply(new BigDecimal(2));
		BigDecimal xn1 = fx.divide(fpx, 2 * (new BigDecimal(150)).intValue(),
				(new BigDecimal(10).pow((new BigDecimal(150)).intValue())).ROUND_HALF_DOWN);
		xn1 = xn.add(xn1.negate());
		// ----
		BigDecimal currentSquare = xn1.pow(2);
		BigDecimal currentPrecision = currentSquare.subtract(c);
		currentPrecision = currentPrecision.abs();
		if (currentPrecision.compareTo(precision) <= -1) {
			return xn1;
		}
		return sqrtNewtonRaphson(c, xn1, precision);
	}
}