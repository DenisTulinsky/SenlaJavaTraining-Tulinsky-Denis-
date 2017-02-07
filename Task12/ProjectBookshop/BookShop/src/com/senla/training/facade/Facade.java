package com.senla.training.facade;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.DI.DI;
import com.senla.training.dao.BookDAO;
import com.senla.training.dao.OrderDAO;
import com.senla.training.dao.PreorderDAO;
import com.senla.training.enums.Status;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.ICSVFileWorker;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.properties.PropertyFactory;
import com.senla.training.tools.ObjectCreator;


public class Facade implements IFacade {

	private IBookService bservice;
	private ICSVFileWorker fw;
	private IConverterReadableString converterReadableString;
	private IConverter converter;
	private IPreorderService preodservice;
	private IOrderService orderservice;
	private final org.apache.log4j.Logger log = Logger.getLogger(Facade.class);

	private BookDAO bookDao;
	private PreorderDAO preorderDao;
	private OrderDAO orderDao;

	@Override
	public void init() {
		bookDao = new BookDAO();
		orderDao = new OrderDAO();
		preorderDao = new PreorderDAO();
		converterReadableString = (IConverterReadableString) DI.load(IConverterReadableString.class);
		bservice = (IBookService) DI.load(IBookService.class, converterReadableString, bookDao);
		preodservice = (IPreorderService) DI.load(IPreorderService.class, converterReadableString, preorderDao);
		orderservice = (IOrderService) DI.load(IOrderService.class, converterReadableString, orderDao);
		converter = (IConverter) DI.load(IConverter.class);
		fw = (ICSVFileWorker) DI.load(ICSVFileWorker.class, converter);

	}

	@Override
	public Boolean addBook(String title, String author, GregorianCalendar publishedDate, Status status, Integer price,
			GregorianCalendar arrivalDate, String description) {
		Boolean flag = false;
		try {
			Book book = (Book) ObjectCreator.createBook(title, author, publishedDate, status, price, arrivalDate,
					description);
			flag = bservice.addBook(book);

			if (Boolean.valueOf(PropertyFactory.getProps().getValue("executePreorders"))) {
				preodservice.checkPreorders(book.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return flag;
	}

	@Override
	public Boolean removeBook(Integer id) {
		return bservice.removeBook(id);
	}

	@Override
	public List<String> showAllBooksSortByTitle() {

		String sortBy = "title";
		return bservice.showAllBooks(sortBy);
	}

	@Override
	public List<String> showAllBooksSortByPrice() {
		String sortBy = "price";
		return bservice.showAllBooks(sortBy);

	}

	@Override
	public List<String> showAllBooksSortByInStock() {
		String sortBy = "status";
		return bservice.showAllBooks(sortBy);

	}

	public List<String> showAllBooksSortByPublDate() {
		String sortBy = "published_date";
		return bservice.showAllBooks(sortBy);
	}

	public List<String> showUnwantedBooksByArrDate() {
		String sortBy = "arrival_date";
		return bservice.showUnwantedBooks(sortBy);
	}

	public List<String> showUnwantedBooksByPrice() {
		String sortBy = "price";
		return bservice.showUnwantedBooks(sortBy);
	}

	@Override
	public Boolean writeBooksToCsv() {
		return fw.writeBooksToCsv(bookDao);
	}

	@Override
	public Boolean readBooksFromCsv() {
		return fw.readBooksFromCsv(bookDao);
	}

	@Override
	public Boolean writeOrdersToCsv() {
		return fw.writeOrdersToCsv(orderDao);
	}

	@Override
	public Boolean readOrdersFromCsv() {
		return fw.readOrdersFromCsv(orderDao);
	}

	@Override
	public Boolean writePreordersToCsv() {
		return fw.writePreordersToCsv(preorderDao);
	}

	@Override
	public Boolean readPreordersFromCsv() {
		return fw.readPreordersFromCsv(preorderDao);
	}

	public Boolean addPreorder(Integer bookId) {
		Boolean flag = false;
		try {
			flag = preodservice.addPreorder(ObjectCreator.createPreorder(bookId));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return flag;
	}

	public Boolean addOrder(String customer, Calendar executiondate, Integer bookid) {
		Boolean flag = false;
		try {
			flag = orderservice.addOrder((Order) ObjectCreator.createOrder(customer, executiondate, bookid));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return flag;
	}

	public Boolean cancelOrder(Integer id) {
		return orderservice.cancelOrder(id);
	}

	public Boolean executeOrder(Integer id) {
		return orderservice.executeOrder(id);

	}

	public List<String> showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2) {
		String sortBy = "exec_date";
		return orderservice.showExecOrdersByPeriod(calendar1, calendar2, sortBy);

	}

	public List<String> showExecOrdersByPeriodSortByPrice(Calendar calendar1, Calendar calendar2) {
		String sortBy = "price";
		return orderservice.showExecOrdersByPeriod(calendar1, calendar2, sortBy);

	}

	public List<String> showOrdersByDate() {
		String sortBy = "exec_date";
		return orderservice.showAllOrders(sortBy);
	}

	public List<String> showOrdersByPrice() {
		String sortBy = "price";
		return orderservice.showAllOrders(sortBy);

	}

	public List<String> showOrdersByStatus() {
		String sortBy = "status";
		return orderservice.showAllOrders(sortBy);

	}

	public Integer showExecOrdersCount(Calendar c1, Calendar c5) {
		return orderservice.showExecOrdersCount(c1, c5);

	}

	public Integer showMoneyByPeriod(Calendar c1, Calendar c5) {
		return orderservice.showMoneyByPeriod(c1, c5);

	}

	public List<String> viewBookDescription(Integer id) {
		return bservice.viewBookDescription(id);

	}

	public List<String> viewOrderDetail(Integer id) {
		return orderservice.viewOrderDetail(id);
	}

	public List<String> showPreordersByBook() {
		String sortBy = "title";
		return preodservice.showAllPreorders(sortBy);
	}

	///
	public List<String> showPreordersByNumber() {
		String sortBy = "count";
		return preodservice.showAllPreorders(sortBy);

	}
	@Override
	public Boolean cloneOrder(Integer id) {
		Boolean cloned = false;
		try {
			cloned = orderservice.cloneOrder(id);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return cloned;
	}
}
