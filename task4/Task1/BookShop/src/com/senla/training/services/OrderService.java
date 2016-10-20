package com.senla.training.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.interfaces.IPrinter;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Order;

public class OrderService implements IOrderService {
	private IStorage storage;
	private IPrinter printer;

	public OrderService(IStorage storage, IPrinter printer) {
		this.storage = storage;
		this.printer = printer;
	}

	@Override
	public void addOrder(String title, String author, String customer, int price, Calendar executiondate) {
		IOrder order = new Order();
		List<IBook> books = storage.getAllBooks();
		for (IBook b : books) {
			if (b != null && b.getTitle().toString().equals(title.toString())
					&& b.getAuthor().toString().equals(author.toString())) {

				order.setBook(b);
				order.setPrice(b.getPrice());

				break;
			}

		}
		order.setCustomer(customer);
		order.setExecutionDate(executiondate.getTime());
		storage.addOrder(order);

	}

	@Override
	public void executeOrder(String title, String author, String customer, int price, Calendar executiondate) {
		storage.executeOrder(title, author, customer, price, executiondate.getTime());

	}

	@Override
	public void cancelOrder(String title, String author, String customer, int price, Calendar executiondate) {
		storage.cancelOrder(title, author, customer, price, executiondate.getTime());

	}

	@Override
	public void showAllOrders(Comparator<IOrder> com) {
		List<IOrder> allOrders = storage.getAllOrders();
		if (com != null) {
			Collections.sort(allOrders, com);
		}
		for (IOrder ord : allOrders) {
			printer.print(ord);

		}
	}

	@Override
	public void showExecOrdersByPeriod(Calendar cal3, Calendar cal4, Comparator<IOrder> com) {
		List<IOrder> allOrders = storage.getAllOrders();
		List<IOrder> periodOrders = new ArrayList<>();
		for (IOrder ord : allOrders) {
			if (ord.getStatus()) {
				Calendar executiondate = new GregorianCalendar();
				executiondate.setTime(ord.getExecutionDate());
				if (executiondate.after(cal3) && executiondate.before(cal4)) {
					periodOrders.add(ord);
				}
			}
		}
		Collections.sort(periodOrders, com);
		for (IOrder ord : periodOrders) {
			printer.print(ord);

		}

	}

	public void showExecOrdersCount(Calendar cal3, Calendar cal4) {
		List<IOrder> allOrders = storage.getAllOrders();
		List<IOrder> periodOrders = new ArrayList<>();
		for (IOrder ord : allOrders) {
			if (ord.getStatus()) {
				Calendar executiondate = new GregorianCalendar();
				executiondate.setTime(ord.getExecutionDate());
				if (executiondate.after(cal3) && executiondate.before(cal4)) {
					periodOrders.add(ord);
				}
			}
		}

		System.out.println("Количество выполненных заказов:" + periodOrders.size());
	}

	@Override
	public void showMoneyByPeriod(Calendar c1, Calendar c5) {
		List<IOrder> allOrders = storage.getAllOrders();
		Integer sum = 0;
		for (IOrder ord : allOrders) {
			if (ord.getStatus()) {
				Calendar executiondate = new GregorianCalendar();
				executiondate.setTime(ord.getExecutionDate());
				if (executiondate.after(c1) && executiondate.before(c5)) {
					sum += ord.getPrice();
				}
			}
		}
		System.out.println("Сумма заработанных средств:" + sum);
	}

	@Override
	public void viewOrderDetail(String title, String author) {
		List<IOrder> allOrders = storage.getAllOrders();
		for (IOrder ord : allOrders) {
			if (ord != null && ord.getBook().getTitle().toString().equals(title.toString())
					&& ord.getBook().getAuthor().toString().equals(author.toString())) {
				printer.print(ord);
				break;
			}

		}

	}

}
