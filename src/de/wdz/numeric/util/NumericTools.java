package de.wdz.numeric.util;

import java.math.BigDecimal;
import java.math.MathContext;

public interface NumericTools {

	default double absoluteError(double rn, double fn) {
		return Math.abs(rn - fn);
	}

	default double relativeError(double rn, double fn) {
		return Math.abs((rn - fn) / rn);
	}

	default BigDecimal absoluteError(BigDecimal rn, BigDecimal fn) {
		return rn.subtract(fn, MathContext.DECIMAL128).abs();
	}

	default BigDecimal relativeError(BigDecimal rn, BigDecimal fn) {
		return (rn.subtract(fn, MathContext.DECIMAL128)).divide(rn, MathContext.DECIMAL128).abs();
	}
}