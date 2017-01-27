package com.senla.training.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.connection.ConnectorDB;
import com.senla.training.dao.BookDAO;
import com.senla.training.dao.OrderDAO;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.tools.Status;

public class OrderService implements IOrderService {
	private IStorage storage;
	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(OrderService.class);

	public OrderService(IStorage storage, IConverterReadableString converterToString) {
		this.storage = storage;
		this.converterToString = converterToString;
	}

	@Override
	public synchronized void addOrder(Order order, String bookid) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		BookDAO bookDao = new BookDAO(cn);
		Book book = bookDao.findEntityById(bookid);
		order.setBook(book);
		order.setPrice(book.getPrice());
		order.setStatus(Status.ACTIVE);
		order.setId(UUID.randomUUID().toString());

		OrderDAO orderDao = new OrderDAO(cn);
		orderDao.add(order);

	}

	@Override
	public synchronized void executeOrder(String id) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		OrderDAO orderDao = new OrderDAO(cn);
		orderDao.updateStatus(id, Status.EXECUTED.toString());
	}

	@Override
	public synchronized void cancelOrder(String id) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		OrderDAO orderDao = new OrderDAO(cn);
		orderDao.updateStatus(id, Status.CANCELED.toString());
	}

	@Override
	public synchronized List<String> showAllOrders(String sortBy) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		OrderDAO orderDao = new OrderDAO(cn);
		List<Order> allOrders = orderDao.findAll(sortBy);

		List<String> result = new ArrayList<>();
		for (Order o : allOrders) {
			result.add(converterToString.convert(o));
		}
		return result;

	}

	@Override
	public synchronized List<String> showExecOrdersByPeriod(Calendar cal3, Calendar cal4, Comparator<IOrder> com) {
		List<IOrder> allOrders = storage.getAllOrders();
		List<IOrder> periodOrders = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for (IOrder ord : allOrders) {
			if (ord.getStatus() == Status.EXECUTED) {
				Calendar executiondate = new GregorianCalendar();
				executiondate.setTime(ord.getExecutionDate());
				if (executiondate.after(cal3) && executiondate.before(cal4)) {
					periodOrders.add(ord);
				}
			}
		}
		Collections.sort(periodOrders, com);
		for (IOrder ord : periodOrders) {

			result.add(converterToString.convert(ord));

		}
		return result;
	}

	public synchronized Integer showExecOrdersCount(Calendar cal3, Calendar cal4) {
		List<IOrder> allOrders = storage.getAllOrders();
		List<IOrder> periodOrders = new ArrayList<>();
		for (IOrder ord : allOrders) {
			if (ord.getStatus() == Status.EXECUTED) {
				Calendar executiondate = new GregorianCalendar();
				executiondate.setTime(ord.getExecutionDate());
				if (executiondate.after(cal3) && executiondate.before(cal4)) {
					periodOrders.add(ord);
				}
			}
		}
		return periodOrders.size();
	}

	@Override
	public synchronized Integer showMoneyByPeriod(Calendar c1, Calendar c5) {
		List<IOrder> allOrders = storage.getAllOrders();
		Integer sum = 0;
		for (IOrder ord : allOrders) {
			if (ord.getStatus() == Status.EXECUTED) {
				Calendar executiondate = new GregorianCalendar();
				executiondate.setTime(ord.getExecutionDate());
				if (executiondate.after(c1) && executiondate.before(c5)) {
					sum += ord.getPrice();
				}
			}
		}
		return sum;
	}

	@Override
	public synchronized List<String> viewOrderDetail(String id) {
		List<String> result = null;

		Connection cn = ConnectorDB.getInstance().getConnection();
		OrderDAO orderDao = new OrderDAO(cn);
		Order order = orderDao.findEntityById(id);

		try {
			result = AnnotationsWorker.createAnnotation(order);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * Searches for the order to clone by book title and customer. Clones order
	 * and modifies id. Adds the cloned order to storage
	 *
	 */
	@Override
	public synchronized void cloneOrder(String title, String customer) {
		List<IOrder> orders = storage.getAllOrders();
		try {
			for (IOrder ord : orders) {
				if (ord != null && ord.getBook().getTitle().equals(title) && ord.getCustomer().equals(customer)) {

					IOrder clonedOrder = ord.clone();
					clonedOrder.setId(UUID.randomUUID().toString());
					storage.addOrder(clonedOrder);
					break;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
