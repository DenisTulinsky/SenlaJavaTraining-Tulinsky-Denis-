package com.senla.training.preordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;

public class SortPreordByBook extends AbstrAction {

	public SortPreordByBook(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(IFacade facade, IInputReader input) {

		Printer.printList(facade.showPreordersByBook());

	}

}
