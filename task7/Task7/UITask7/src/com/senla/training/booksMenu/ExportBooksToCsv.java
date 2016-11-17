package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;

public class ExportBooksToCsv extends AbstrAction {

	private static final String COMPLETED = "Export completed";
	private static final String ERROR = "Error";

	public ExportBooksToCsv(String name, Integer id) {
		super(name,id);
	
}
	
	@Override
	public void action(IFacade facade, IInputReader input) {
		Boolean result = facade.writeBooksToCsv();

		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}

	}

}
