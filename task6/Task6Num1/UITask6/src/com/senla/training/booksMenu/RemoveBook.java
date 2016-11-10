package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;

public class RemoveBook extends AbstrAction {

	private static final String ERROR = "\nError";
	private static final String BOOK_REMOVED = "\nBook removed";
	public static final String TITLE = "Enter Book's Title";
	

	public RemoveBook(String name, Integer id) {
		super(name,id);
	}

	@Override
	public void action(IFacade facade, InputReader input) {
		
		Printer.printString(TITLE);
		String title = input.getString();
		Boolean result = facade.removeBook(title);
		
		if(result){
			
			Printer.printString(BOOK_REMOVED);
		}else{
			Printer.printString(ERROR);
		}
	}

	
}
