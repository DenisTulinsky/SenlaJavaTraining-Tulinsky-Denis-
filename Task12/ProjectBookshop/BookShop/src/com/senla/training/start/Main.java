package com.senla.training.start;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.training.DI.DI;
import com.senla.training.enums.Status;
import com.senla.training.interfaces.IFacade;

public class Main {
	
	

	public static void main(String[] args) {
		
		IFacade facade =   (IFacade) DI.load(IFacade.class); 
		facade.init();
		
		facade.addBook("q", "q", (GregorianCalendar)Calendar.getInstance(), Status.INSTOCK, 3, (GregorianCalendar)Calendar.getInstance(), "q");

	}

}
