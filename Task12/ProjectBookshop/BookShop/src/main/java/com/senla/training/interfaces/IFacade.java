package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.senla.training.enums.Status;

public interface IFacade {
	public void init();

	public Boolean addBook(String title, String author, GregorianCalendar publishedDate, Status status, Integer price,
			GregorianCalendar arrivalDate, String description);

	public Boolean setBookStockStatus(Integer id, Status status);

	public List<String> showAllBooksSortByTitle();

	public List<String> showAllBooksSortByPrice();

	public List<String> showAllBooksSortByInStock();

	public Boolean writeBooksToCsv();

	public Boolean writeOrdersToCsv();

	public Boolean writePreordersToCsv();

	public Boolean readBooksFromCsv();

	public Boolean readOrdersFromCsv();

	public Boolean readPreordersFromCsv();

	public Boolean addPreorder(Integer bookId);

	public Boolean addOrder(String customer, Calendar executiondate, List<Integer> ids);

	public Boolean cancelOrder(Integer id);

	public Boolean executeOrder(Integer id);

	public List<String> showAllBooksSortByPublDate();

	public List<String> showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2);

	public List<String> showExecOrdersByPeriodSortByPrice(Calendar c1, Calendar c5);

	public List<String> showOrdersByDate();

	public List<String> showOrdersByPrice();

	public List<String> showOrdersByStatus();

	public Long showExecOrdersCount(Calendar c1, Calendar c5);

	public Long showMoneyByPeriod(Calendar c1, Calendar c5);

	public List<String> viewBookDescription(Integer id);

	public List<String> viewOrderDetail(Integer id);

	public List<String> showUnwantedBooksByArrDate();

	public List<String> showUnwantedBooksByPrice();

	public List<String> showPreordersByBook();

	public List<String> showPreordersByNumber();

	public Boolean cloneOrder(Integer id);

	public Boolean deleteBook(Integer id);

	Boolean deleteOrder(Integer id);
}
