/**
 * 
 */
package com.senla.training.ordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.Printer;

/**
 * @author Denis
 *
 */
public class CloneOrder extends AbstrAction {

	
	private static final String ORDER_CLONED = "Order Cloned";
	public static final String TITLE = "Enter Book's Title";
	private static final String CUSTOMER = "Enter customer's name:";
	private static final String ERROR = "\nError";
	public CloneOrder(String name, Integer id) {
		super(name, id);
	}
	
	/* (non-Javadoc)
	 * @see com.senla.training.interfaces.IAction#action(com.senla.training.interfaces.IFacade, com.senla.training.tools.InputReader)
	 */
	@Override
	public void action(IFacade facade, InputReader input) {
		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Boolean result = facade.cloneOrder(title, customer);
		if (result) {

			Printer.printString(ORDER_CLONED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
