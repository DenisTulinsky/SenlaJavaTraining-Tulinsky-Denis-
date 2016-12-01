package com.senla.training.ordersMenu;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class SortAllByDate extends AbstrAction {

	public SortAllByDate(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {
		Object[] method = { "showOrdersByDate" };
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("methodName", method);

		Printer.printList(transmitter.toServer(map));

	}

}
