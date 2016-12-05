package com.senla.training.addidionalActions;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.interfaces.IMenuItem;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class Serialize extends AbstrAction implements IMenuItem {

	private static final String COMPLETED = "Serialization completed";
	private static final String ERROR = "Error";

	public Serialize(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Object[] parameters = null;
		Request request = new Request("serialize", parameters);

		Boolean result = transmitter.toServer(request);

		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}
	}
}
