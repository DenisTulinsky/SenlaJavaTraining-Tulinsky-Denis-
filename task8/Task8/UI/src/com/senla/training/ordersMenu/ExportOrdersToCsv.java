package com.senla.training.ordersMenu;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class ExportOrdersToCsv extends AbstrAction {

	private static final String COMPLETED = "Export completed";
	private static final String ERROR = "Error";

	public ExportOrdersToCsv(String name, Integer id) {
		super(name, id);

	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Object[] method = { "writeOrdersToCsv" };

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
