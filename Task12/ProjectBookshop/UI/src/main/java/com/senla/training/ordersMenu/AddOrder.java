package com.senla.training.ordersMenu;

import java.util.GregorianCalendar;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

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
	public void action(Transmitter transmitter, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(EXECUTION_DATE);
		GregorianCalendar executiondate = input.getDate();
		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Object[] parameters = { customer, executiondate, title};
		Request request = new Request("addOrder", parameters);

		Boolean result = transmitter.toServer(request);
		
		if (result) {
			Printer.printString(ORDER_CREATED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
