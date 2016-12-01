package com.senla.training.booksMenu;

import java.util.HashMap;
import java.util.Map;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;


public class ViewDescription extends AbstrAction {
	public static final String TITLE = "Enter Book's Title";

	public ViewDescription(String name, Integer id) {
		super(name, id);
	}
    
	@Override
	public void action(Transmitter trans, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();

		Object[] method = { "viewBookDescription" };
		Object[] parameters = { title };

		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("methodName", method);
		map.put("params", parameters);

		
		Printer.printList(trans.toServer(map));
	}

	

}
