package com.senla.training.facade;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.senla.training.DI.DI;
import com.senla.training.enums.Status;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IOrderDAO;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPreorderDAO;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.properties.PropertyFactory;

public class Facade implements IFacade {

	private static final String EXECUTE_PREORDERS = "executePreorders";
	private static final String B_TITLE = "b.title";
	private static final String COUNT = "count";
	private static final String EXEC_DATE = "executionDate";
	private static final String PUBLISHED_DATE = "publishedDate";
	private static final String ARRIVAL_DATE = "arrivalDate";
	private static final String STATUS = "status";
	private static final String PRICE = "price";
	private static final String TITLE = "title";
	private IBookService bservice;
	private IConverterReadableString converterReadableString;
	private IPreorderService preodservice;
	private IOrderService orderservice;

	@Override
	public void init() {
		IBookDAO bookDao = (IBookDAO) DI.load(IBookDAO.class);
		IOrderDAO orderDao = (IOrderDAO) DI.load(IOrderDAO.class);
		IPreorderDAO preorderDao = (IPreorderDAO) DI.load(IPreorderDAO.class);
		converterReadableString = (IConverterReadableString) DI.load(IConverterReadableString.class);
		bservice = (IBookService) DI.load(IBookService.class, converterReadableString, bookDao);
		preodservice = (IPreorderService) DI.load(IPreorderService.class, converterReadableString, preorderDao,
				bookDao);
		orderservice = (IOrderService) DI.load(IOrderService.class, converterReadableString, orderDao, bookDao);
	}

	@Override
	public Boolean addBook(String title, String author, GregorianCalendar publishedDate, Status status, Integer price,
			GregorianCalendar arrivalDate, String description) {
		return bservice.addBook(title, author, publishedDate, status, price, arrivalDate, description);
	}

	@Override
	public Boolean deleteBook(Integer id) {
		return bservice.deleteBook(id);
	}

	@Override
	public Boolean deleteOrder(Integer id) {
		return orderservice.deleteOrder(id);
	}

	@Override
	public Boolean setBookStockStatus(Integer id, Status status) {
		if (status.equals(Status.INSTOCK)) {
			if (Boolean.valueOf(PropertyFactory.getProps().getValue(EXECUTE_PREORDERS))) {
				bservice.updateStatus(id, status);
				preodservice.updateStatus(id);
				return true;
			} else {
				return bservice.updateStatus(id, status);
			}
		} else {
			return bservice.updateStatus(id, status);
		}
	}

	@Override
	public List<String> showAllBooksSortByTitle() {
		return bservice.showAllBooks(TITLE);
	}

	@Override
	public List<String> showAllBooksSortByPrice() {
		return bservice.showAllBooks(PRICE);
	}

	@Override
	public List<String> showAllBooksSortByInStock() {
		return bservice.showAllBooks(STATUS);
	}

	public List<String> showAllBooksSortByPublDate() {
		return bservice.showAllBooks(PUBLISHED_DATE);
	}

	public List<String> showUnwantedBooksByArrDate() {
		return bservice.showUnwantedBooks(ARRIVAL_DATE);
	}

	public List<String> showUnwantedBooksByPrice() {
		return bservice.showUnwantedBooks(PRICE);
	}

	@Override
	public Boolean writeBooksToCsv() {
		return bservice.writeBooksToCsv();
	}

	@Override
	public Boolean readBooksFromCsv() {
		return bservice.readBooksFromCsv();
	}

	@Override
	public Boolean writeOrdersToCsv() {
		return orderservice.writeOrdersToCsv();
	}

	@Override
	public Boolean readOrdersFromCsv() {
		return orderservice.readOrdersFromCsv();
	}

	@Override
	public Boolean writePreordersToCsv() {
		return preodservice.writePreordersToCsv();
	}

	@Override
	public Boolean readPreordersFromCsv() {
		return preodservice.readPreordersFromCsv();
	}

	public Boolean addPreorder(Integer bookId) {
		return preodservice.addPreorder(bookId);
	}

	public Boolean addOrder(String customer, Calendar executiondate, List<Integer> ids) {
		return orderservice.addOrder(customer, executiondate, ids);
	}

	public Boolean cancelOrder(Integer id) {
		return orderservice.updateStatus(id, Status.CANCELED);
	}

	public Boolean executeOrder(Integer id) {
		return orderservice.updateStatus(id, Status.EXECUTED);
	}

	public List<String> showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2) {
		return orderservice.showExecOrdersByPeriod(calendar1, calendar2, EXEC_DATE);
	}

	public List<String> showExecOrdersByPeriodSortByPrice(Calendar calendar1, Calendar calendar2) {
		return orderservice.showExecOrdersByPeriod(calendar1, calendar2, PRICE);
	}

	public List<String> showOrdersByDate() {
		return orderservice.showAllOrders(EXEC_DATE);
	}

	public List<String> showOrdersByPrice() {
		return orderservice.showAllOrders(PRICE);
	}

	public List<String> showOrdersByStatus() {
		return orderservice.showAllOrders(STATUS);
	}

	public Long showExecOrdersCount(Calendar c1, Calendar c5) {
		return orderservice.showExecOrdersCount(c1, c5);
	}

	public Long showMoneyByPeriod(Calendar c1, Calendar c5) {
		return orderservice.showMoneyByPeriod(c1, c5);
	}

	public List<String> viewBookDescription(Integer id) {
		return bservice.viewBookDescription(id);
	}

	public List<String> viewOrderDetail(Integer id) {
		return orderservice.viewOrderDetail(id);
	}

	public List<String> showPreordersByBook() {
		return preodservice.showAllPreorders(B_TITLE);
	}

	public List<String> showPreordersByNumber() {
		return preodservice.showAllPreorders(COUNT);
	}

	@Override
	public Boolean cloneOrder(Integer id) {
		return orderservice.cloneOrder(id);
	}
}
