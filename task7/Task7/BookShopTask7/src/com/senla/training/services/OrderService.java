package com.senla.training.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IStorage;
import com.senla.training.status.Status;

public class OrderService implements IOrderService {
	private IStorage storage;
	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(OrderService.class);

	public OrderService(IStorage storage, IConverterReadableString converterToString) {
		this.storage = storage;
		this.converterToString = converterToString;
	}

	@Override
	public void addOrder(IOrder order, String title) {
		List<IBook> books = storage.getAllBooks();
		Boolean nobook = true;

		for (IBook b : books) {
			if (b != null && b.getTitle().equals(title)) {
				order.setBook(b);
				order.setPrice(b.getPrice());
				order.setStatus(Status.ACTIVE);
				order.setId(UUID.randomUUID().toString());
				storage.addOrder(order);
				nobook = false;
				break;
			}
		}
		if (nobook)
			throw new NullPointerException();
	}

	@Override
	public void executeOrder(String booktitle, String customer) {
		List<IOrder> orders = storage.getAllOrders();
		try {
			for (IOrder ord : orders) {
				if (ord != null && ord.getBook().getTitle().equals(booktitle) && ord.getCustomer().equals(customer)) {
					ord.setExecutionDate(Calendar.getInstance().getTime());
					ord.setStatus(Status.EXECUTED);
					break;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void cancelOrder(String booktitle, String customer) {
		List<IOrder> orders = storage.getAllOrders();
		try {
			for (IOrder ord : orders) {
				if (ord != null && ord.getBook().getTitle().equals(booktitle) && ord.getCustomer().equals(customer)) {
					ord.setStatus(Status.CANCELED);
					break;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public List<String> showAllOrders(Comparator<IOrder> com) {
		List<IOrder> allOrders = storage.getAllOrders();
		List<String> result = new ArrayList<>();
		if (com != null) {
			Collections.sort(allOrders, com);
		}
		for (IOrder ord : allOrders) {
			result.add(converterToString.convert(ord));
		}
		return result;
	}

	@Override
	public List<String> showExecOrdersByPeriod(Calendar cal3, Calendar cal4, Comparator<IOrder> com) {
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

	public Integer showExecOrdersCount(Calendar cal3, Calendar cal4) {
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
	public Integer showMoneyByPeriod(Calendar c1, Calendar c5) {
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
	public List<String> viewOrderDetail(String bookstitle, String customer) {
		List<IOrder> allOrders = storage.getAllOrders();
		List<String> result = new ArrayList<>();
		try{
		for (IOrder ord : allOrders) {

				if (ord != null && ord.getBook().getTitle().equals(bookstitle) && ord.getCustomer().equals(customer)) {
					try {
						result = AnnotationsWorker.createAnnotation(ord);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						log.error(e.getMessage());
					}
				}
			}
		}catch (Exception e) {
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
	public void cloneOrder(String title, String customer) {
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
