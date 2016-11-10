package com.senla.training.interfaces;

import java.util.List;

public interface IStorage {
	public void addBook(IBook book);

	public void addPreorder(IPreorder preorder);

	public List<IBook> getAllBooks();

	public void addOrder(IOrder order);

	public List<IOrder> getAllOrders();

	public List<IPreorder> getAllPreorders();

}
