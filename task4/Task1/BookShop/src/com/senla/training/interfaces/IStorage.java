package com.senla.training.interfaces;


import java.util.Date;
import java.util.List;

public interface IStorage {
	public void addBook(IBook book);
	public void addPreorder(IPreorder preorder);
	public List<IBook> getAllBooks();
	public void removeBook(String title, String author, Date publishedDate, Boolean inStock, Integer price,
			Date arrivalDate, String description);
	public void checkPreorder(String title, String author, Date publishedDate);
	public void addOrder(IOrder order);
	public List<IOrder> getAllOrders();
	public void cancelOrder(String title, String author, String customer, int price, Date executiondate);
	public void executeOrder(String title, String author, String customer, int price, Date executiondate);
	public List<IPreorder> getAllPreorders();
	
	
}
