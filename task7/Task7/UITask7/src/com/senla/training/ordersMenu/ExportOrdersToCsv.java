package com.senla.training.ordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;

public class ExportOrdersToCsv extends AbstrAction {

	private static final String COMPLETED = "Export completed";
	private static final String ERROR = "Error";

	public ExportOrdersToCsv(String name, Integer id) {
		super(name,id);
	
}
	
	@Override
	public void action(IFacade facade, IInputReader input) {
		Boolean result = facade.writeOrdersToCsv();

		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}

	}

}
