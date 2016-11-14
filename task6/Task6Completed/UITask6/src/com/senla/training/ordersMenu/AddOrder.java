package com.senla.training.ordersMenu;

import java.util.GregorianCalendar;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.ObjectCreator;
import com.senla.training.tools.Printer;

public class AddOrder extends AbstrAction {

	private static final String CUSTOMER = "Enter customer's name:";
	private static final String ERROR = "\nError";
	private static final String ORDER_CREATED = "\nOrder Created";
	private static final String EXECUTION_DATE = "Please enter the execution date in format: yyyy.MM.dd";
	public static final String TITLE = "Enter Book's Title";

	public AddOrder(String name, Integer id) {

		super(name, id);
	}

	@Override
	public void action(IFacade facade, InputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();

		Printer.printString(EXECUTION_DATE);
		GregorianCalendar executiondate = input.getDate();

		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Boolean result = facade.addOrder(ObjectCreator.createOrder(customer, executiondate), title);
		if (result) {

			Printer.printString(ORDER_CREATED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
