package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class RemoveBook extends AbstrAction {

	private static final String ERROR = "\nError";
	private static final String BOOK_REMOVED = "\nBook removed";
	public static final String TITLE = "Enter Book's Title";
	

	public RemoveBook(String name, Integer id) {
		super(name,id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {
		
		Printer.printString(TITLE);
		String title = input.getString();
		
		Object[] parameters = {title};
		
		Request request = new Request("removeBook", parameters);

		Boolean result = transmitter.toServer(request);
		
		if(result){
			
			Printer.printString(BOOK_REMOVED);
		}else{
			Printer.printString(ERROR);
		}
	}

	
}
