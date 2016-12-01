package com.senla.training.addidionalActions;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.interfaces.IMenuItem;
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
		Object[] method = { "serialize" };

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("methodName", method);

		Boolean result = transmitter.toServer(map);

		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}
	}
}
