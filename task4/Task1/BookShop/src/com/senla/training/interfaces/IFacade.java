package com.senla.training.interfaces;

import java.util.Calendar;

public interface IFacade {
	public void init();

	public void addBook(String title, String author, Calendar publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description);

	public void removeBook(String title, String author, Calendar publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description);

	public void writeToFile();

	public void readFromFile();

	public void addOrder(String title, String author, String Customer, int price, Calendar executiondate);

	public void showAllBooksSortByTitle();

	public void showAllBooksSortByPrice();

	public void showAllBooksSortByInStock();

}
