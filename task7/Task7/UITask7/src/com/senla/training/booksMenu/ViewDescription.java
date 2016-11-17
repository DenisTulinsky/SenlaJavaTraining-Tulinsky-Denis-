package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;

public class ViewDescription extends AbstrAction {
	public static final String TITLE = "Enter Book's Title";
	

	public ViewDescription(String name, Integer id) {
		super(name,id);
	}

	@Override
	public void action(IFacade facade, IInputReader input) {
		
		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printList(facade.viewBookDescription(title));
		
	}

	

}
