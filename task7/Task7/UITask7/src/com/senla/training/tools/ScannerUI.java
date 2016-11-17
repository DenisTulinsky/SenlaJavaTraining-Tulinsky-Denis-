package com.senla.training.tools;

import java.util.Scanner;

import com.senla.training.interfaces.IScannerUI;

public class ScannerUI implements IScannerUI {

	private Scanner sc;

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IScannerUI#getString()
	 */
	@Override
	public String getString() {
		sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/* (non-Javadoc)
	 * @see com.senla.training.tools.IScannerUI#closeScan()
	 */
	@Override
	public void closeScan() {
		sc.close();
	}

}
