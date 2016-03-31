package de.ostfalia;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test implements BigDecimalFunction {

	public static void main(String[] args) {
		Test test = new Test();
//		BigDecimal bigDecimal = test.fac(new BigDecimal(100));
//		System.out.println(bigDecimal);
//		BigDecimal bigDecimal2 = test.pow(new BigDecimal(100), 2);
//		System.out.println(bigDecimal2);
		BigDecimal sin = test.sin(new BigDecimal(1, MathContext.DECIMAL128), 1000);
		System.out.println("+sin \t" + sin);
		System.out.println("-sin \t" +Math.sin(1));
	}

	// public static void main(String[] args) {
	// double x = 1.;
	// double h = 10.;
	// for (int k = 0; k <= 16; k++) {
	// double res = ((x + (Math.pow(h, k))) - (x - (Math.pow(h, k)))) / (2 *
	// (Math.pow(h, k)));
	// System.out.println(Math.pow(h, k));
	// System.out.println(res);
	// }
	// testLambdas();
	// }

	public static void testLambdas() {
		List<String> myList = Arrays.asList("element1", "element2", "element3");
		myList.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});

		List<String> myList2 = Arrays.asList("element1", "element2", "element3");
		myList2.forEach(element -> System.out.println(element));
	}
}