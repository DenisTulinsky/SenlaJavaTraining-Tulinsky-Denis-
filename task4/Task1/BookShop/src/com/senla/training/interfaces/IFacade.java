package com.senla.training.interfaces;

import java.util.Calendar;

public interface IFacade {
	public void init();

	public void addBook(String title, String author, Calendar publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description);

	public void removeBook(String title, String author, Calendar publishedDate, Boolean inStock, Integer price,
			Calendar arrivalDate, String description);

	public void showAllBooksSortByTitle();

	public void showAllBooksSortByPrice();

	public void showAllBooksSortByInStock();

	public void writeToFile();

	public void readFromFile();

	public void addPreorder(String title, String author, Calendar publishedDate);

	public void addOrder(String title, String author, String Customer, int price, Calendar executiondate);

	public void cancelOrder(String title, String author, String Customer, int price, Calendar executiondate);

	public void executeOrder(String title, String author, String Customer, int price, Calendar executiondate);

	public void showAllPreorders();

	public void showAllBooksSortByPublDate();

	public void showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2);

	public void showExecOrdersByPeriodSortByPrice(Calendar c1, Calendar c5);

	public void showOrdersByDate();

	public void showOrdersByPrice();

	public void showOrdersByStatus();

	public void showExecOrdersCount(Calendar c1, Calendar c5);

	public void showMoneyByPeriod(Calendar c1, Calendar c5);

	public void viewBookDescription(String title, String author);

	public void viewOrderDetail(String title, String author);

	public void showUnwantedBooksByArrDate();

	public void showUnwantedBooksByPrice();

	public void showPreordersByBook();

	public void showPreordersByNumber();

}
