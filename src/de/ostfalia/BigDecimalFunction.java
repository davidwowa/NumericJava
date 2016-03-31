package de.ostfalia;

import java.math.BigDecimal;
import java.math.MathContext;

public interface BigDecimalFunction {

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

	default BigDecimal cos(BigDecimal value) {
		return null;
	}

	default BigDecimal tan(BigDecimal value) {
		return null;
	}

	default BigDecimal sec(BigDecimal value) {
		return null;
	}

	default BigDecimal test(BigDecimal value) {
		return null;
	}
}