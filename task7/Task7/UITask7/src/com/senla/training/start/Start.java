package com.senla.training.start;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.INavigator;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		INavigator navigator = (INavigator) DI.load(INavigator.class);
		
		navigator.navigate();

	}


}
 
