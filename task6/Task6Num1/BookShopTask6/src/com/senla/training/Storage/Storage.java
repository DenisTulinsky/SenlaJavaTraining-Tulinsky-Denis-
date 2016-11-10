package com.senla.training.Storage;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IStorage;
import com.senla.training.tools.IdGenerator;

public class Storage implements IStorage {

	private List<IBook> books;
	private List<IPreorder> preorders;
	private List<IOrder> orders;

	public Storage() {
		books = new ArrayList<IBook>();
		preorders = new ArrayList<IPreorder>();
		orders = new ArrayList<IOrder>();
	}

	@Override
	public void addBook(IBook book) {
		book.setId(IdGenerator.generateBookId());
		books.add(book);

	}

	@Override
	public List<IBook> getAllBooks() {

		return books;
	}

	@Override
	public void addPreorder(IPreorder preorder) {
		preorders.add(preorder);

	}

	@Override
	public void addOrder(IOrder order) {
		orders.add(order);

	}

	@Override
	public List<IOrder> getAllOrders() {

		return orders;
	}

	@Override
	public List<IPreorder> getAllPreorders() {

		return preorders;
	}
	
	

}
