package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.List;

public interface IFacade {
	public void init();

	public Boolean addBook(IBook book);

	public Boolean removeBook(String string);

	public List<String> showAllBooksSortByTitle();

	public List<String> showAllBooksSortByPrice();

	public List<String> showAllBooksSortByInStock();

	public Boolean writeBooksToCsv();
	public Boolean writeOrdersToCsv();
	public Boolean writePreordersToCsv();

	public Boolean readBooksFromCsv();
	public Boolean readOrdersFromCsv();
	public Boolean readPreordersFromCsv();

	public Boolean addPreorder(IPreorder preorder);

	public Boolean addOrder(IOrder order, String title);

	public Boolean cancelOrder(String booktitle, String customer);

	public Boolean executeOrder(String booktitle, String customer);

	public List<String> showAllBooksSortByPublDate();

	public List<String> showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2);

	public List<String> showExecOrdersByPeriodSortByPrice(Calendar c1, Calendar c5);

	public List<String> showOrdersByDate();

	public List<String> showOrdersByPrice();

	public List<String> showOrdersByStatus();

	public Integer showExecOrdersCount(Calendar c1, Calendar c5);

	public Integer showMoneyByPeriod(Calendar c1, Calendar c5);

	public List<String> viewBookDescription(String title);

	public List<String> viewOrderDetail(String bookstitle, String customer);

	public List<String> showUnwantedBooksByArrDate();

	public List<String> showUnwantedBooksByPrice();

	public List<String> showPreordersByBook();

	public List<String> showPreordersByNumber();

	public Boolean cloneOrder(String title, String customer);

	public void serialize();
	
}
