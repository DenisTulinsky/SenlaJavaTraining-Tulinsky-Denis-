package com.senla.training.ordersMenu;

import java.util.GregorianCalendar;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;

public class SortExecOrdersByDate extends AbstrAction {

	private static final String END_DATE = "Enter end date:";
	private static final String START_DATE = "Enter start date:";

	public SortExecOrdersByDate(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(IFacade facade, InputReader input) {

		Printer.printString(START_DATE);
		GregorianCalendar startdate = input.getDate();

		Printer.printString(END_DATE);
		GregorianCalendar enddate = input.getDate();

		Printer.printList(facade.showExecOrdersByPeriodSortByDate(startdate, enddate));

	}

}
