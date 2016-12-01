package com.senla.training.preordersMenu;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class AddPreorder extends AbstrAction {

	public static final String TITLE = "Enter Book's Title";
	public static final String AUTHOR = "Enter Author";
	private static final String ERROR = "\nError";
	private static final String PREORDER_CREATED = "\nPreorder created";

	public AddPreorder(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(AUTHOR);
		String author = input.getString();
		
		Object[] method = {"addPreorder"};
		Object[] parameters = {title, author};

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("methodName", method);
		map.put("params", parameters);

		Boolean result = transmitter.toServer(map);
		
		if (result) {

			Printer.printString(PREORDER_CREATED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
