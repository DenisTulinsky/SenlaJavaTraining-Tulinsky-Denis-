package com.senla.training.tools;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.training.enums.Status;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ObjectCreator {

	public static Preorder createPreorder(Integer bookId) {
		Preorder preord = new Preorder();
		preord.setBookId(bookId);
		return preord;
	}

	public static Book createBook(String title, String author, GregorianCalendar publishedDate, Status inStock,
			Integer price, GregorianCalendar arrivalDate, String description) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublishedDate(publishedDate.getTime());
		book.setInStock(inStock);
		book.setPrice(price);
		book.setArrivalDate(arrivalDate.getTime());
		book.setDescription(description);
		return book;
	}

	public static Order createOrder(String customer, Calendar executionDate, Integer bookId) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setExecutionDate(executionDate.getTime());
		order.setBookId(bookId);
		return order;
	}

}
