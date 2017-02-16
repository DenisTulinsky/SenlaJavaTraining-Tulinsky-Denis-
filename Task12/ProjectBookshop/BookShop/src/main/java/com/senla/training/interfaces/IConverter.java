package com.senla.training.interfaces;

import org.hibernate.Session;

import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public interface IConverter {
	public String bookToString(Book book);

	public Book stringToBook(String string);

	public String orderToString(Order order);

	public Order stringToOrder(Session session, String string);

	public String preorderToString(Preorder preord);

	public Preorder stringToPreorder(Session session, String string);
}
