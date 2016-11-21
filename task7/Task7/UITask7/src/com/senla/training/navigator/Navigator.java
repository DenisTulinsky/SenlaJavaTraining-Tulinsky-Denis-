package com.senla.training.navigator;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.IAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.interfaces.IMenu;
import com.senla.training.interfaces.IMenuBuilder;
import com.senla.training.interfaces.IMenuItem;
import com.senla.training.interfaces.INavigator;
import com.senla.training.interfaces.IScannerUI;
import com.senla.training.tools.Printer;

public class Navigator implements INavigator {

	private static final String BYE = "\nBye";
	private static final String CHOOSE = "\nChoose Option";
	private static final String WRONG_OPTION = "Wrong Option";
	private IMenu mainmenu;
	private IMenu currentMenu;
	private IScannerUI scanner;
	private IFacade facade =   (IFacade) DI.load(IFacade.class);  //new Facade();
	private IMenuBuilder menubuilder;
	private IInputReader input;
	

	public Navigator() {
		menubuilder = (IMenuBuilder) DI.load(IMenuBuilder.class); 
		mainmenu = menubuilder.build();
		scanner =(IScannerUI) DI.load(IScannerUI.class);
		input = (IInputReader) DI.load(IInputReader.class,scanner);
		facade.init();
		
		
	}
	

	/* (non-Javadoc)
	 * @see com.senla.training.navigator.INavigator#navigate()
	 */
	@Override
	public void navigate() {

		currentMenu = mainmenu;
		Printer.printString(CHOOSE);
		Printer.printMenu(currentMenu.getMenu());

		do {
			Integer choice = input.getInteger();
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
		
		facade.serialize();
		Printer.printString(BYE);

	}

	private IMenu getCurrentMenu() {
		return currentMenu;

	}
}
