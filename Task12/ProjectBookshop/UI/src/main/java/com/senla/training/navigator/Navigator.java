package com.senla.training.navigator;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.IAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.interfaces.IMenu;
import com.senla.training.interfaces.IMenuBuilder;
import com.senla.training.interfaces.IMenuItem;
import com.senla.training.interfaces.INavigator;
import com.senla.training.interfaces.IScannerUI;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class Navigator implements INavigator {

	private static final String BYE = "\nBye";
	private static final String CHOOSE = "\nChoose Option";
	private static final String WRONG_OPTION = "Wrong Option";
	private IMenu mainmenu;
	private IMenu currentMenu;
	private IScannerUI scanner;
	private IMenuBuilder menubuilder;
	private IInputReader input;

	public Navigator() {
		menubuilder = (IMenuBuilder) DI.load(IMenuBuilder.class);
		mainmenu = menubuilder.build();
		scanner = (IScannerUI) DI.load(IScannerUI.class);
		input = (IInputReader) DI.load(IInputReader.class, scanner);
	}

	@Override
	public void navigate(Transmitter transmitter) {
		Boolean noexit = true;
		currentMenu = mainmenu;
		Printer.printString(CHOOSE);
		Printer.printMenu(currentMenu.getMenu());

		do {
			Integer choice = input.getInteger();
			if (choice > (currentMenu.getMenu().size())) {
				Printer.printString(WRONG_OPTION);
			}

			for (IMenuItem i : getCurrentMenu().getMenu()) {
				if (choice.equals(i.getId()) && i instanceof IMenu) {
					currentMenu = (IMenu) i;
					Printer.printString(CHOOSE);
					Printer.printMenu(((IMenu) i).getMenu());

				} else if (choice.equals(i.getId()) && i instanceof IAction && !"Exit".equals(i.getName())) {
					IAction a = (IAction) i;
					a.action(transmitter, input);

					Printer.printString(CHOOSE);
					Printer.printMenu(currentMenu.getMenu());

				}
				if (choice.equals(i.getId()) && i.getName().equals("Exit")) {
					IAction a = (IAction) i;
					a.action(transmitter, input);
					noexit = false;
				}
			}
		} while (noexit);  
		scanner.closeScan();
		transmitter.disconnect();
		Printer.printString(BYE);
	}
	private IMenu getCurrentMenu() {
		return currentMenu;

	}
}
