package com.senla.training.preordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.ObjectCreator;
import com.senla.training.tools.Printer;

public class AddPreorder extends AbstrAction {

	public static final String TITLE = "Enter Book's Title";
	public static final String AUTHOR = "Enter Author";
	private static final String ERROR = "\nError";
	private static final String PREORDER_CREATED = "\nPreorder created";

	public AddPreorder(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(IFacade facade, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(AUTHOR);
		String author = input.getString();

		Boolean result = facade.addPreorder(ObjectCreator.createPreorder(title, author));
		if (result) {

			Printer.printString(PREORDER_CREATED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
