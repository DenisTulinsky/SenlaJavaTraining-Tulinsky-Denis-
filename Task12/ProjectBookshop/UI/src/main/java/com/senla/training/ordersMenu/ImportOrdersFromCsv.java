package com.senla.training.ordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class ImportOrdersFromCsv extends AbstrAction {

	private static final String COMPLETED = "Import completed";
	private static final String ERROR = "Error";

	public ImportOrdersFromCsv(String name, Integer id) {
		super(name, id);

	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Object[] parameters = null;
		Request request = new Request("readOrdersFromCsv", parameters);
		
		Boolean result = transmitter.toServer(request);
		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}

	}

}
