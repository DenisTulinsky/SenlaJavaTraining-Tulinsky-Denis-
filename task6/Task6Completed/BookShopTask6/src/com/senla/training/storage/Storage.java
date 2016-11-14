package com.senla.training.storage;

import java.io.Serializable;
import java.util.ArrayList;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IStorage;

/**
 * Contains collections of objects. Methods to add objects and retrieve lists of objects.
 */
public class Storage implements IStorage, Serializable {

	
	private static final long serialVersionUID = 1L;
	

	private ArrayList<IBook> books;
	private ArrayList<IPreorder> preorders;
	private ArrayList<IOrder> orders;

	public Storage() {
		books = new ArrayList<IBook>();
		preorders = new ArrayList<IPreorder>();
		orders = new ArrayList<IOrder>();
	}

	@Override
	public void addBook(IBook book) {
		Boolean nonexisting = true;
		for (IBook b : books) {
			if (b.getId().equals(book.getId())) {
				nonexisting = false;

				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
				b.setArrivalDate(book.getArrivalDate());
				b.setInStock(book.getInStock());
				b.setPrice(book.getPrice());
				b.setPublishedDate(book.getPublishedDate());
				b.setDescription(book.getDescription());
			}
		}
		if (nonexisting) {

			books.add(book);
		}

	}

	@Override
	public ArrayList<IBook> getAllBooks() {

		return books;
	}

	@Override
	public void addPreorder(IPreorder preorder) {
		Boolean nonexisting = true;
		for (IPreorder p : preorders) {
			if (p.getId().equals(preorder.getId())) {
				nonexisting = false;

				p.setTitle(preorder.getTitle());
				p.setAuthor(preorder.getAuthor());
				p.setCount(preorder.getCount());
				p.setStatus(preorder.getStatus());

			}
		}
		if (nonexisting) {

			preorders.add(preorder);
		}
	}

	@Override
	public void addOrder(IOrder order) {
		Boolean nonexisting = true;
		for (IOrder o : orders) {
			if (o.getId().equals(order.getId())) {
				nonexisting = false;

				o.setBook(order.getBook());
				o.setCustomer(order.getCustomer());
				o.setExecutionDate(order.getExecutionDate());
				o.setPrice(order.getPrice());
				o.setStatus(order.getStatus());
			}
		}
		if (nonexisting) {

			orders.add(order);
		}

	}

	@Override
	public ArrayList<IOrder> getAllOrders() {

		return orders;
	}

	@Override
	public ArrayList<IPreorder> getAllPreorders() {

		return preorders;
	}

}
