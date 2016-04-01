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

	// Newton Method
	default BigDecimal sqrt(BigDecimal x, int limit, int initialGuess) {
		BigDecimal two = new BigDecimal(2, MathContext.DECIMAL128);
		BigDecimal x0 = new BigDecimal(initialGuess, MathContext.DECIMAL128);
		for (int i = 0; i <= limit; i++) {
			BigDecimal pow = pow(x0, 2);
			BigDecimal fx0 = pow.subtract(x, MathContext.DECIMAL128);
			BigDecimal f_x0 = two.multiply(x0, MathContext.DECIMAL128);
			BigDecimal subtrahend = fx0.divide(f_x0, MathContext.DECIMAL128);
			x0 = x0.subtract(subtrahend, MathContext.DECIMAL128);
		}
		return x0;
	}

	default BigDecimal euler(BigDecimal x, int limit) {
		BigDecimal result = new BigDecimal(0, MathContext.DECIMAL128);
		for (int i = 0; i <= limit; i++) {
			BigDecimal fac = fac(new BigDecimal(i, MathContext.DECIMAL128));
			result = result.add(BigDecimal.ONE.divide(fac, MathContext.DECIMAL128), MathContext.DECIMAL128);
		}
		return result;
	}

	/**
	 * @url https://de.wikipedia.org/wiki/Kreiszahl
	 */
	default BigDecimal PI(BigDecimal tropfenzahl) {
		BigDecimal pi = new BigDecimal(0);
		BigDecimal innerhalb = new BigDecimal(0);
		BigDecimal gesamt = tropfenzahl;

		while (gesamt.intValue() > 0) { // generiere Tropfen und addiere je nach
			// Zugehörigkeit
			BigDecimal dotx = random();
			BigDecimal doty = random();

			System.out.println(dotx);
			System.out.println(doty);
			
			if (((dotx.multiply(dotx, MathContext.DECIMAL128)).add((doty.multiply(doty, MathContext.DECIMAL128)),
					MathContext.DECIMAL128)).intValue() <= 1) {
				// Punkt liegt innerhalb des Kreises
				innerhalb = innerhalb.add(BigDecimal.ONE, MathContext.DECIMAL128);
			} else {
				// Punkt liegt außerhalb des Kreises
			}
			gesamt = gesamt.subtract(BigDecimal.ONE, MathContext.DECIMAL128);
		}

		// pi = 4 * (double) innerhalb / tropfenzahl;
		pi = (new BigDecimal(4, MathContext.DECIMAL128)).multiply(innerhalb.divide(tropfenzahl, MathContext.DECIMAL128),
				MathContext.DECIMAL128);
		return pi;
	}

	default BigDecimal random() {
		BigDecimal max = new BigDecimal(Integer.MAX_VALUE - 1 + ".0");
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		BigDecimal actualRandomDec = randFromDouble.divide(max, BigDecimal.ROUND_DOWN);
		return actualRandomDec;
	}
}