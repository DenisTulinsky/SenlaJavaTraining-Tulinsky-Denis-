package com.senla.training.facade;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.comparators.BookByArrivalDate;
import com.senla.training.comparators.BookByPriceComparator;
import com.senla.training.comparators.BookByPublDateComparator;
import com.senla.training.comparators.BookByStatusComparator;
import com.senla.training.comparators.BookByTitleComp;
import com.senla.training.comparators.OrderByDateComp;
import com.senla.training.comparators.OrderByPriceComp;
import com.senla.training.comparators.OrderByStatusComp;
import com.senla.training.comparators.PreordersByNumberComp;
import com.senla.training.comparators.PreordersByTitleComp;
import com.senla.training.fileWorker.FileWorker;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IFileWorker;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.interfaces.IStorage;
import com.senla.training.properties.Property;
import com.senla.training.services.BookService;
import com.senla.training.services.OrderService;
import com.senla.training.services.PreorderService;
import com.senla.training.storage.Storage;
import com.senla.training.tools.Converter;
import com.senla.training.tools.ConverterReadableString;
import com.senla.training.tools.SerializationUtility;

public class Facade implements IFacade {
	private IStorage storage;
	private BookService bservice;
	private IFileWorker fw;
	private IConverterReadableString printer;
	private IConverter converter;
	private IPreorderService preodservice;
	private IOrderService orderservice;
	private final org.apache.log4j.Logger log = Logger.getLogger(Facade.class);
	private SerializationUtility serializationUtil;
	
	@Override
	public void init() {
		serializationUtil = new SerializationUtility();
		storage = serializationUtil.deserialize();
		if (storage == null) {
			storage = new Storage();
				}
		printer = new ConverterReadableString();
		bservice = new BookService(storage, printer);
		preodservice = new PreorderService(storage, printer);
		orderservice = new OrderService(storage, printer);
		converter = new Converter(storage);
		fw = new FileWorker(converter);

	}

	@Override
	public Boolean addBook(IBook book) {
		try {
			bservice.addBook(book);

			if (Property.getProps().getExecTag()) {
				preodservice.checkPreorders(book.getTitle(), book.getAuthor());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Boolean removeBook(String title) {

		try {
			bservice.removeBook(title);

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public List<String> showAllBooksSortByTitle() {
		Comparator<IBook> byTitle = new BookByTitleComp();
		return bservice.showAllBooks(byTitle);
	}

	@Override
	public List<String> showAllBooksSortByPrice() {
		Comparator<IBook> byPrice = new BookByPriceComparator();
		return bservice.showAllBooks(byPrice);

	}

	@Override
	public List<String> showAllBooksSortByInStock() {
		Comparator<IBook> byInStock = new BookByStatusComparator();

		return bservice.showAllBooks(byInStock);

	}

	public List<String> showAllBooksSortByPublDate() {
		Comparator<IBook> byPublDate = new BookByPublDateComparator();

		return bservice.showAllBooks(byPublDate);
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
		
	
	
	
	
	

	public Boolean addPreorder(IPreorder preorder) {
		try {
			preodservice.addPreorder(preorder);

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public Boolean addOrder(IOrder order, String title) {
		try {
			orderservice.addOrder(order, title);

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;

	}

	public Boolean cancelOrder(String booktitle, String customer) {
		try {
			orderservice.cancelOrder(booktitle, customer);

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;

	}

	public Boolean executeOrder(String booktitle, String customer) {
		try {
			orderservice.executeOrder(booktitle, customer);

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
		Comparator<IOrder> byExecDate = new OrderByDateComp();

		return orderservice.showAllOrders(byExecDate);
	}

	public List<String> showOrdersByPrice() {
		Comparator<IOrder> byPrice = new OrderByPriceComp();

		return orderservice.showAllOrders(byPrice);

	}

	public List<String> showOrdersByStatus() {
		Comparator<IOrder> byStatus = new OrderByStatusComp();

		return orderservice.showAllOrders(byStatus);

	}

	public Integer showExecOrdersCount(Calendar c1, Calendar c5) {
		return orderservice.showExecOrdersCount(c1, c5);

	}

	public Integer showMoneyByPeriod(Calendar c1, Calendar c5) {
		return orderservice.showMoneyByPeriod(c1, c5);

	}

	public List<String> viewBookDescription(String title) {
		return bservice.viewBookDescription(title);

	}

	public List<String> viewOrderDetail(String bookstitle, String customer) {

		return orderservice.viewOrderDetail(bookstitle, customer);

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
	public void serialize() {
		serializationUtil.serialize(storage);

	}

}
