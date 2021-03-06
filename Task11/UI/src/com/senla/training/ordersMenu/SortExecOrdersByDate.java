package com.senla.training.ordersMenu;

import java.util.GregorianCalendar;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class SortExecOrdersByDate extends AbstrAction {

	private static final String END_DATE = "Enter end date:";
	private static final String START_DATE = "Enter start date:";

	public SortExecOrdersByDate(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Printer.printString(START_DATE);
		GregorianCalendar startdate = input.getDate();

		Printer.printString(END_DATE);
		GregorianCalendar enddate = input.getDate();

		Object[] parameters = {startdate, enddate};

		Request request = new Request("showExecOrdersByPeriodSortByDate", parameters);

		Printer.printList(transmitter.toServer(request));
		
		

	}

}
