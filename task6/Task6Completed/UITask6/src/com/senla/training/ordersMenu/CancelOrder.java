package com.senla.training.ordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;

public class CancelOrder extends AbstrAction {

	public static final String TITLE = "Enter Book's Title";
	private static final String CUSTOMER = "Enter customer's name:";
	private static final String ORDER_CANCELLED = "\nOrder Cancelled";
	private static final String ERROR = "\nError";

	

	public CancelOrder(String name, Integer id) {
		super(name,id);
	}

	@Override
	public void action(IFacade facade, InputReader input) {
		
		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Boolean result = facade.cancelOrder(title, customer);

		if (result) {

			Printer.printString(ORDER_CANCELLED);
		} else {
			Printer.printString(ERROR);
		}
	}

	

}
