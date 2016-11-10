package com.senla.training.tools;

import java.util.Scanner;

public class ScannerUI {

	private static final String NOT_A_NUMBER = "Вы ввели не число";
	private Scanner sc;

	public Integer getInteger() {
		sc = new Scanner(System.in);
		Integer option = null;
		if (sc.hasNextInt()) {
			option = sc.nextInt();
		} else {
			Printer.printString(NOT_A_NUMBER);
		}

		return option;

	}

	public String getString() {
		sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public void closeScan() {
		sc.close();
	}

}
