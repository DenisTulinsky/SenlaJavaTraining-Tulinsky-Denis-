package com.senla.training.ordersMenu;

import java.util.GregorianCalendar;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;

public class CountExecOrdersMoney extends AbstrAction {

	private static final String END_DATE = "Enter end date:";
	private static final String START_DATE = "Enter start date:";

	public CountExecOrdersMoney(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(IFacade facade, IInputReader input) {

		Printer.printString(START_DATE);
		GregorianCalendar startdate = input.getDate();

		Printer.printString(END_DATE);
		GregorianCalendar enddate = input.getDate();

		Printer.printInteger(facade.showMoneyByPeriod(startdate, enddate));
	}

}
