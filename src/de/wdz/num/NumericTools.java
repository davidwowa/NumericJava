package de.wdz.num;

import java.math.BigDecimal;
import java.math.MathContext;

public interface NumericTools {

	default double absoluteError(double rn, double fn) {
		return rn - fn;
	}

	default double relativeError(double rn, double fn) {
		return (rn - fn) / rn;
	}

	default BigDecimal absoluteError(BigDecimal rn, BigDecimal fn) {
		return rn.subtract(fn, MathContext.DECIMAL128);
	}

	default BigDecimal relativeError(BigDecimal rn, BigDecimal fn) {
		return (rn.subtract(fn, MathContext.DECIMAL128)).divide(rn, MathContext.DECIMAL128);
	}
}