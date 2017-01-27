package com.senla.training.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface IStorage extends Serializable {
	public void addBook(IBook book);

	public void addPreorder(IPreorder preorder);

	public ArrayList<IBook> getAllBooks();

	public void addOrder(IOrder order);

	public ArrayList<IOrder> getAllOrders();

	public ArrayList<IPreorder> getAllPreorders();

}
