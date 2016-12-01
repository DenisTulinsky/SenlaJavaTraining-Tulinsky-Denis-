package com.senla.training.booksMenu;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
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
		
		Object[] method = {"removeBook"};
		Object[] parameters = {title};

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("methodName", method);
		map.put("params", parameters);

		Boolean result = transmitter.toServer(map);
		
		if(result){
			
			Printer.printString(BOOK_REMOVED);
		}else{
			Printer.printString(ERROR);
		}
	}

	
}
