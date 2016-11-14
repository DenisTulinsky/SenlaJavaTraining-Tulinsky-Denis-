package com.senla.training.preordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;

public class ImportPreordersFromCsv extends AbstrAction {

	private static final String COMPLETED = "Import completed";
	private static final String ERROR = "Error";

	public ImportPreordersFromCsv(String name, Integer id) {
		super(name,id);
	
}
	
	@Override
	public void action(IFacade facade, InputReader input) {
		Boolean result = facade.readPreordersFromCsv();

		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}

	}

}