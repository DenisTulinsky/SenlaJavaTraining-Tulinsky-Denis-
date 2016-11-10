package com.senla.training.preordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;

public class SortPreordByNumber extends AbstrAction {

	public SortPreordByNumber(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(IFacade facade, InputReader input) {
		Printer.printList(facade.showPreordersByNumber());

	}

}
