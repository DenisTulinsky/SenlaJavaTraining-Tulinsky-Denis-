package com.senla.training.facade;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.DI.DI;
import com.senla.training.comparators.BookByArrivalDate;
import com.senla.training.comparators.BookByPriceComparator;
import com.senla.training.comparators.OrderByDateComp;
import com.senla.training.comparators.OrderByPriceComp;
import com.senla.training.comparators.PreordersByNumberComp;
import com.senla.training.comparators.PreordersByTitleComp;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.ICSVFileWorker;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.interfaces.ISerializationUtility;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.tools.ObjectCreator;
import com.senla.training.tools.SerializationUtility;
import com.senla.training.tools.Status;

public class Facade implements IFacade {
	private IStorage storage;
	private IBookService bservice;
	private ICSVFileWorker fw;
	private IConverterReadableString converterReadableString;
	private IConverter converter;
	private IPreorderService preodservice;
	private IOrderService orderservice;
	private final org.apache.log4j.Logger log = Logger.getLogger(Facade.class);
	private ISerializationUtility serializationUtil;

	@Override
	public void init() {
		serializationUtil = new SerializationUtility();
		storage = serializationUtil.deserialize();
		if (storage == null) {
			storage = (IStorage) DI.load(IStorage.class);
		}
		converterReadableString = (IConverterReadableString) DI.load(IConverterReadableString.class);
		bservice = (IBookService) DI.load(IBookService.class, storage, converterReadableString);
		preodservice = (IPreorderService) DI.load(IPreorderService.class, storage, converterReadableString);
		orderservice = (IOrderService) DI.load(IOrderService.class, storage, converterReadableString);
		converter = (IConverter) DI.load(IConverter.class, storage);
		fw = (ICSVFileWorker) DI.load(ICSVFileWorker.class, converter);

	}

	@Override
	public Boolean addBook(String title, String author, GregorianCalendar publishedDate, Status status, Integer price,
			GregorianCalendar arrivalDate, String description) {
		try {
			bservice.addBook((Book)ObjectCreator.createBook(title, 
					author, publishedDate, status, price, arrivalDate, description));
			//if (Boolean.valueOf(PropertyFactory.getProps().getValue("executePreorders"))) {
				//preodservice.checkPreorders(title, author);
			//}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean removeBook(String id) {
		try {
			bservice.removeBook(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
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
		Comparator<IBook> byArrDate = new BookByArrivalDate();
		return bservice.showUnwantedBooks(byArrDate);
	}

	public List<String> showUnwantedBooksByPrice() {
		Comparator<IBook> byPrice = new BookByPriceComparator();
		return bservice.showUnwantedBooks(byPrice);
	}

	@Override
	public Boolean writeBooksToCsv() {
		try {
			fw.writeBooksToCsv(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean readBooksFromCsv() {
		try {
			fw.readBooksFromCsv(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean writeOrdersToCsv() {
		try {
			fw.writeOrdersToCsv(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean readOrdersFromCsv() {
		try {
			fw.readOrdersFromCsv(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean writePreordersToCsv() {
		try {
			fw.writePreordersToCsv(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean readPreordersFromCsv() {
		try {
			fw.readPreordersFromCsv(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public Boolean addPreorder(String title, String author) {
		try {
			preodservice.addPreorder(ObjectCreator.createPreorder(title, author));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public Boolean addOrder(String customer, Calendar executiondate, String bookid) {
		try {
			orderservice.addOrder((Order) ObjectCreator.createOrder(customer, executiondate), bookid);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;

	}

	public Boolean cancelOrder(String id) {
		try {
			orderservice.cancelOrder(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;

	}

	public Boolean executeOrder(String id) {
		try {
			orderservice.executeOrder(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;

	}

	public List<String> showExecOrdersByPeriodSortByDate(Calendar calendar1, Calendar calendar2) {
		Comparator<IOrder> ByDate = new OrderByDateComp();
		return orderservice.showExecOrdersByPeriod(calendar1, calendar2, ByDate);

	}

	public List<String> showExecOrdersByPeriodSortByPrice(Calendar c1, Calendar c5) {
		Comparator<IOrder> ByPrice = new OrderByPriceComp();
		return orderservice.showExecOrdersByPeriod(c1, c5, ByPrice);

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

	public List<String> viewBookDescription(String id) {
		return bservice.viewBookDescription(id);

	}

	public List<String> viewOrderDetail(String id) {
		return orderservice.viewOrderDetail(id);
	}

	public List<String> showPreordersByBook() {
		Comparator<IPreorder> byTitle = new PreordersByTitleComp();
		return preodservice.showAllPreorders(byTitle);
	}

	public List<String> showPreordersByNumber() {
		Comparator<IPreorder> byNumber = new PreordersByNumberComp();
		return preodservice.showAllPreorders(byNumber);

	}

	@Override
	public Boolean cloneOrder(String title, String customer) {
		try {
			orderservice.cloneOrder(title, customer);

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean serialize() {
		try {
			serializationUtil.serialize(storage);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
}
