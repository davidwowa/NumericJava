package de.wdz.task2;

import de.wdz.functions.BigDecimalFunctions;
import de.wdz.functions.DoubleFunctions;
import de.wdz.num.NumericTools;
import de.wdz.num.Strategy;

public class Task2 implements DoubleFunctions, BigDecimalFunctions, NumericTools, Strategy {
	private int iterations = 1000;
	private int guess = 10;

	private int limit = 100;

	@Override
	public void runBigDecimal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void runDouble() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Task2 task2 = new Task2();
		task2.runDouble();
		System.out.println("---");
		task2.runBigDecimal();
	}
}