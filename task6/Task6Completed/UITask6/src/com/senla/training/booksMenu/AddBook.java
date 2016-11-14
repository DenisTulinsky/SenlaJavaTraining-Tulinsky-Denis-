package com.senla.training.booksMenu;

import java.util.GregorianCalendar;

import com.senla.training.abstractClass.AbstrAction;
import com.senla.training.interfaces.IFacade;
import com.senla.training.status.Status;
import com.senla.training.tools.InputReader;
import com.senla.training.tools.ObjectCreator;
import com.senla.training.tools.Printer;

public class AddBook extends AbstrAction   {

	private static final String ERROR = "\nError";
	private static final String BOOK_ADDED = "\nBook added";
	public static final String TITLE = "Enter Book's Title";
	public static final String AUTHOR = "Enter Author";
	public static final String BEGIN_DATE = "Please enter the begin date in format: yyyy.MM.dd";
	public static final String END_DATE = "Please enter the end date in format: yyyy.MM.dd";
	public static final String PUBLISHED_DATE = "Please enter the publication date in format: yyyy";
	private static final String PRICE = "Enter Price:";
	private static final String ARRIVAL_DATE = "Please enter the arrival date in format: yyyy.MM.dd";
	private static final String DESCRIPTION = "Enter Description:";
	

	public AddBook(String name, Integer id) {
			super(name,id);
		
	}

	@Override
	
		public void action(IFacade facade, InputReader input) {
		
		Printer.printString(TITLE);
		String title = input.getString();
		Printer.printString(AUTHOR);
		String author = input.getString();
		Printer.printString(PUBLISHED_DATE);
		GregorianCalendar publdate = input.getDate();
		Status status = Status.INSTOCK;
		Printer.printString(PRICE);
		Integer price = input.getInteger();
		Printer.printString(ARRIVAL_DATE);
		GregorianCalendar arrdate = input.getDate();
		Printer.printString(DESCRIPTION);
		String description = input.getString();

		Boolean result = facade.addBook(ObjectCreator.createBook(title, author, publdate, status, price, arrdate, description));

		if (result) {

			Printer.printString(BOOK_ADDED);
		} else {
			Printer.printString(ERROR);
		}
	}

	

	

}
