package com.senla.training.booksMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.tools.Printer;



public class SortByInStock extends AbstrAction {
	
   
	public SortByInStock(String name, Integer id){
		super(name,id);
	}
		

	@Override
	public void action(IFacade facade, IInputReader input) {
		
		Printer.printList(facade.showAllBooksSortByInStock());
	}

	
}
