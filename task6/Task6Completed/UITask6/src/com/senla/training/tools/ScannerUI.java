package com.senla.training.tools;

import java.util.Scanner;

public class ScannerUI {

	private Scanner sc;

	public String getString() {
		sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public void closeScan() {
		sc.close();
	}

}
