package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;


public class ViewDescription extends AbstrAction {
	public static final String TITLE = "Enter Book's Title";

	public ViewDescription(String name, Integer id) {
		super(name, id);
	}
    
	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();

		Object[] parameters = { title };
		
		Request request = new Request("viewBookDescription", parameters);

		Printer.printList(transmitter.toServer(request));
	}

	

}
