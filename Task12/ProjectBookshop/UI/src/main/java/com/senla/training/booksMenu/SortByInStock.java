package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;



public class SortByInStock extends AbstrAction {
	
   
	public SortByInStock(String name, Integer id){
		super(name,id);
	}
		

	@Override
	public void action(Transmitter transmitter, IInputReader input) {
		
		Object[] parameters = null;
		Request request = new Request("showAllBooksSortByInStock", parameters);

		Printer.printList(transmitter.toServer(request));
		
	}

	
}
