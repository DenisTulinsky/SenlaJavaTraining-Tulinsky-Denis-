package com.senla.training.tools;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ObjectCreator {
	
	public static IPreorder createPreorder(String title, String author) {
		return new Preorder(title, author);
	}

	public static IBook createBook(String title, String author, GregorianCalendar publishedDate, Status inStock, Integer price,
			GregorianCalendar arrivalDate, String description) {
		return new Book(title, author, publishedDate.getTime(), inStock, price, arrivalDate.getTime(), description);
	}

		public static IOrder createOrder( String customer, Calendar executiondate) {
		return new Order( customer, executiondate.getTime());
	}

}






