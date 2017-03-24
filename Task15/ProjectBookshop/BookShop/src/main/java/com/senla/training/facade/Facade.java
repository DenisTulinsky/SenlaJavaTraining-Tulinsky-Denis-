package com.senla.training.facade;

import java.util.List;
import java.util.Map;

import com.senla.training.DI.DI;
import com.senla.training.enums.Status;
import com.senla.training.exceptions.MyException;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IFacade;
import com.senla.training.interfaces.IOrderDAO;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPreorderDAO;
import com.senla.training.interfaces.IPreorderService;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;
import com.senla.training.properties.PropertyFactory;

public class Facade implements IFacade {

	private static final String EXECUTE_PREORDERS = "executePreorders";
	private IBookService bservice;
	private IPreorderService preodservice;
	private IOrderService orderservice;

	@Override
	public void init() {
		IBookDAO bookDao = (IBookDAO) DI.load(IBookDAO.class);
		IOrderDAO orderDao = (IOrderDAO) DI.load(IOrderDAO.class);
		IPreorderDAO preorderDao = (IPreorderDAO) DI.load(IPreorderDAO.class);
		bservice = (IBookService) DI.load(IBookService.class, bookDao);
		preodservice = (IPreorderService) DI.load(IPreorderService.class, preorderDao, bookDao);
		orderservice = (IOrderService) DI.load(IOrderService.class, orderDao, bookDao);
	}

	@Override
	public void addBook(Book book) throws MyException {
		bservice.addBook(book);
	}

	@Override
	public  void deleteBook(Integer id) throws MyException {
		 bservice.deleteBook(id);
	}

	@Override
	public void deleteOrder(Integer id) throws MyException {
		 orderservice.deleteOrder(id);
	}

	@Override
	public void updateBook(Book book) throws MyException {
		if (book.getStatus().equals(Status.INSTOCK)) {
			if (Boolean.valueOf(PropertyFactory.getProps().getValue(EXECUTE_PREORDERS))) {
				bservice.updateBook(book);
				preodservice.updateStatus(book.getId());
			} else {
				 bservice.updateBook(book);
			}
		} else {
			 bservice.updateBook(book);
		}
	}

	@Override
	public List<Book> showAllBooks(String criterion) throws MyException {
		return bservice.showAllBooks(criterion);
	}

	public List<Book> showUnwantedBooks(String criterion) throws MyException {
		return bservice.showUnwantedBooks(criterion);
	}

	@Override
	public void writeBooksToCsv() throws MyException {
		 bservice.writeBooksToCsv();
	}

	@Override
	public  void readBooksFromCsv() throws MyException{
		 bservice.readBooksFromCsv();
	}

	@Override
	public void writeOrdersToCsv() throws MyException {
		 orderservice.writeOrdersToCsv();
	}

	@Override
	public void readOrdersFromCsv() throws MyException {
		 orderservice.readOrdersFromCsv();
	}

	@Override
	public Boolean writePreordersToCsv() {
		return preodservice.writePreordersToCsv();
	}

	@Override
	public Boolean readPreordersFromCsv() {
		return preodservice.readPreordersFromCsv();
	}
	@Override
	public Boolean addPreorder(Preorder preorder) {
		return preodservice.addPreorder(preorder);
	}
	@Override
	public void addOrder(Order order) throws MyException {
		 orderservice.addOrder(order);
	}
	@Override
	public void updateOrder(Order order) throws MyException {
		 orderservice.updateOrder(order);
	}

	@Override
	public List<Order> showExecOrdersByPeriod(String startDate, String endDate, String criterion) throws MyException {
		return orderservice.showExecOrdersByPeriod(startDate, endDate, criterion);
	}

	@Override
	public List<Order> showOrders(String criterion) throws MyException {
		return orderservice.showAllOrders(criterion);
	}
	@Override
	public Map<String,Long> showExecOrdersCount(String startDate, String endDate) throws MyException {
		return orderservice.showExecOrdersCount(startDate, endDate);
	}
	@Override
	public Map<String,Long> showMoneyByPeriod(String startDate, String endDate) throws MyException {
		return orderservice.showMoneyByPeriod(startDate, endDate);
	}
	@Override
	public Book viewBookDescription(Integer id) throws MyException {
		return bservice.viewBookDescription(id);
	}
	@Override
	public Order viewOrderDetail(Integer id) throws MyException {
		return orderservice.viewOrderDetail(id);
	}

	@Override
	public List<Preorder> showPreorders(String criterion) {
		return preodservice.showAllPreorders(criterion);
	}

	@Override
	public void cloneOrder(Order order) throws MyException {
		 orderservice.cloneOrder(order);
	}

}
