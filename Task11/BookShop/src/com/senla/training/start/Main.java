package com.senla.training.start;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.IFacade;

public class Main {
	
	

	public static void main(String[] args) {
		
		IFacade facade =   (IFacade) DI.load(IFacade.class); 
		facade.init();
		
		

	}

}
