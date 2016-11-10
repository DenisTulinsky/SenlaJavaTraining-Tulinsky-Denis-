package com.senla.training.navigator;

import com.senla.training.facade.Facade;
import com.senla.training.interfaces.IAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IMenu;
import com.senla.training.interfaces.IMenuItem;
import com.senla.training.menuBuilder.MenuBuilder;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;
import com.senla.training.tools.ScannerUI;

public class Navigator {

	private static final String BYE = "\nBye";
	private static final String CHOOSE = "\nChoose Option";
	private static final String WRONG_OPTION = "Wrong Option";
	private static Navigator navigator;
	private IMenu mainmenu;
	private IMenu currentMenu;
	private ScannerUI scanner;
	private IFacade facade = new Facade();
	private MenuBuilder menubuilder;
	private InputReader input;
	

	private Navigator() {
		menubuilder = new MenuBuilder();
		mainmenu = menubuilder.build();
		scanner = new ScannerUI();
		input = new InputReader(scanner);
		facade.init();
		facade.readFromFile();
	}

	public static Navigator getInstance() {
		if (navigator == null) {
			navigator = new Navigator();
		}
		return navigator;
	}

	public void navigate() {

		currentMenu = mainmenu;
		Printer.printString(CHOOSE);
		Printer.printMenu(currentMenu.getMenu());

		do {
			Integer choice = scanner.getInteger();
			if (choice > (currentMenu.getMenu().size())) {

				Printer.printString(WRONG_OPTION);
			}

			for (IMenuItem i : getCurrentMenu().getMenu()) {
				if (choice.equals(i.getId()) && i instanceof IMenu && !"Exit".equals(i.getName())) {
					currentMenu = (IMenu) i;
					Printer.printString(CHOOSE);
					Printer.printMenu(((IMenu) i).getMenu());

				} else if (choice.equals(i.getId()) && i instanceof IAction) {
					IAction a = (IAction) i;
					a.action(facade,input);
					Printer.printString(CHOOSE);
					Printer.printMenu(currentMenu.getMenu());

				}
				if (choice.equals(i.getId()) && i.getName().equals("Exit")) {
					currentMenu = (IMenu) i;

				}

			}
		}

		while (currentMenu != (IMenu) mainmenu.getMenu().get(3));
		scanner.closeScan();
		facade.writeToFile();
		Printer.printString(BYE);

	}

	private IMenu getCurrentMenu() {
		return currentMenu;

	}
}
