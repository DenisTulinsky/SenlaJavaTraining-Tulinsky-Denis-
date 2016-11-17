package com.senla.training.ordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;

public class ExecuteOrder extends AbstrAction {

	public static final String TITLE = "Enter Book's Title";
	private static final String CUSTOMER = "Enter customer's name:";
	private static final String ORDER_EXECUTED = "\nOrder Executed";
	private static final String ERROR = "\nError";

	public ExecuteOrder(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(IFacade facade, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Boolean result = facade.executeOrder(title, customer);
		if (result) {

			Printer.printString(ORDER_EXECUTED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
