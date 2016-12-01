package com.senla.training.ordersMenu;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class ViewOrderDetail extends AbstrAction {

	public static final String TITLE = "Enter Book's Title";
	private static final String CUSTOMER = "Enter customer's name:";

	public ViewOrderDetail(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Object[] method = { "viewOrderDetail" };
		Object[] parameters = { title, customer };

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("methodName", method);
		map.put("params", parameters);

		Printer.printList(transmitter.toServer(map));

	}

}
