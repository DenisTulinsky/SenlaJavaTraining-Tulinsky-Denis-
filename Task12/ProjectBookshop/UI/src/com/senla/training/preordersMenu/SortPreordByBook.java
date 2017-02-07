package com.senla.training.preordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

public class SortPreordByBook extends AbstrAction {

	public SortPreordByBook(String name, Integer id) {
		super(name, id);
	}

	@Override
	public void action(Transmitter transmitter, IInputReader input) {
		
		Object[] parameters = null;
		Request request = new Request("showPreordersByBook", parameters);
		
		Printer.printList(transmitter.toServer(request));

	}

}
