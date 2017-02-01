package com.senla.training.tools;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ObjectCreator {

	public static IPreorder createPreorder(String bookId) {
		Preorder preord = new Preorder();
		preord.setId(UUID.randomUUID().toString());
		preord.setBookId(bookId);
		return preord;
	}

	public static IBook createBook(String title, String author, GregorianCalendar publishedDate, Status inStock,
			Integer price, GregorianCalendar arrivalDate, String description) {
		return new Book(UUID.randomUUID().toString(),title, author, publishedDate.getTime(), inStock, price, arrivalDate.getTime(), description);
		
	}

	public static IOrder createOrder(String customer, Calendar executiondate, String bookId) {
		return new Order(UUID.randomUUID().toString(), customer, executiondate.getTime(), bookId);
	}

	
}
