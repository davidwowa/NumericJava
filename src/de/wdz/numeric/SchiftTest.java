package de.wdz.numeric;

public class SchiftTest {
	public static void main(String[] args) {
		int x = 200;

		for (int i = 0; i < 10; i++) {
			x = x >> 2;
			System.out.println(x);
		}
	}
}
