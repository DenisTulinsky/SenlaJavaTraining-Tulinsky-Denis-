package com.senla.training.interfaces;

import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public interface IConverter {
	public String bookToString(Book book);

	public Book stringToBook(String string);

	public String orderToString(Order order);

	public Order stringToOrder(String string);

	public String preorderToString(Preorder preord);

	public Preorder stringToPreorder(String string);
}
