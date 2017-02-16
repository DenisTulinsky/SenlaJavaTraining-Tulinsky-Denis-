package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;


public class ExportBooksToCsv extends AbstrAction {

	private static final String COMPLETED = "Export completed";
	private static final String ERROR = "Error";

	public ExportBooksToCsv(String name, Integer id) {
		super(name,id);
	
}
	
	@Override
	public void action(Transmitter transmitter, IInputReader input) {
		
		Object[] parameters = null;
		Request request = new Request("writeBooksToCsv", parameters);
		
		Boolean result = transmitter.toServer(request);
		
		if (result) {

			Printer.printString(COMPLETED);
		} else {
			Printer.printString(ERROR);
		}

	}

}
