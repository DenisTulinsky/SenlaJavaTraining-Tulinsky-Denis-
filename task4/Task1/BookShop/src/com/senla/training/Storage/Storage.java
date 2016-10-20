package com.senla.training.Storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IStorage;

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
		books.add(book);

	}

	@Override
	public void removeBook(String title, String author, Date publishedDate, Boolean inStock, Integer price,
			Date arrivalDate, String description) {
		for (IBook b : books) {
			if (b != null && b.getTitle().toString().equals(title.toString())
					&& b.getAuthor().toString().equals(author.toString()) && b.getPublishedDate().equals(publishedDate)
					&& b.getPrice() == price && b.getDescription().toString().equals(description.toString())) {
				books.remove(b);
				break;
			}
		}

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
	public void checkPreorder(String title, String author, Date publishedDate) {
		for (IPreorder pre : preorders) {
			if (pre != null && pre.getTitle().toString().equals(title.toString())
					&& pre.getAuthor().toString().equals(author.toString())
					&& pre.getPublishedDate().equals(publishedDate)) {

				pre.setStatus(true);

			}
		}
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
	public void cancelOrder(String title, String author, String customer, int price, Date executiondate) {
		for (IOrder ord : orders) {
			if (ord != null && !ord.getStatus() && ord.getBook().getTitle().toString() == title.toString()
					&& ord.getBook().getAuthor().toString().equals(author.toString())
					&& ord.getCustomer().toString().equals(customer.toString()) && ord.getPrice() == price) {
				orders.remove(ord);
				break;
			}
		}

	}

	@Override
	public void executeOrder(String title, String author, String customer, int price, Date executiondate) {
		for (IOrder ord : orders) {
			if (ord != null && ord.getBook().getTitle().toString().equals(title.toString())
					&& ord.getBook().getAuthor().toString().equals(author.toString())
					&& ord.getCustomer().toString().equals(customer.toString()) && ord.getPrice() == price) {

				// ord.setExecutionDate(Calendar.getInstance().getTime());
				ord.setStatus(true);
				break;
			}
		}

	}

	@Override
	public List<IPreorder> getAllPreorders() {

		return preorders;
	}

}
