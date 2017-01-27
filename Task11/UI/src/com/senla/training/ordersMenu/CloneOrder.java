/**
 * 
 */
package com.senla.training.ordersMenu;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IInputReader;
import com.senla.training.requestApi.Request;
import com.senla.training.tools.Printer;
import com.senla.training.tools.Transmitter;

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

	@Override
	public void action(Transmitter transmitter, IInputReader input) {

		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(CUSTOMER);
		String customer = input.getString();

		Object[] parameters = {title, customer};
		Request request = new Request("cloneOrder", parameters);

		Boolean result = transmitter.toServer(request);

		if (result) {
			Printer.printString(ORDER_CLONED);
		} else {
			Printer.printString(ERROR);
		}
	}

}
