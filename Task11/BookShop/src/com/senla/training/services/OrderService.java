package com.senla.training.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.connection.ConnectorDB;
import com.senla.training.dao.OrderDAO;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.model.Order;
import com.senla.training.tools.Status;

public class OrderService implements IOrderService {

	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(OrderService.class);
	private OrderDAO orderDao;

	public OrderService(IConverterReadableString converterToString, OrderDAO orderDao) {
		this.converterToString = converterToString; 
		this.orderDao = orderDao;
	}

	@Override
	public synchronized boolean addOrder(Order order) {
		
		order.setId(UUID.randomUUID().toString());
		order.setStatus(Status.ACTIVE);
		order.setPrice(1 + (int) (Math.random() * 20));
		Connection cn = ConnectorDB.getInstance().getConnection();
		return orderDao.add(cn, order);

	}

	@Override
	public synchronized boolean executeOrder(String id) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		return orderDao.updateStatus(cn, id, Status.EXECUTED.toString());
	}

	@Override
	public synchronized boolean cancelOrder(String id) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		return orderDao.updateStatus(cn, id, Status.CANCELED.toString());
	}

	@Override
	public synchronized List<String> showAllOrders(String sortBy) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		List<Order> allOrders = orderDao.findAllLazy(cn, sortBy);

		List<String> result = new ArrayList<>();
		for (Order o : allOrders) {
			result.add(converterToString.convert(o));
		}
		return result;
	}

	@Override
	public synchronized List<String> showExecOrdersByPeriod(Calendar cal1, Calendar cal2, String sortBy) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		List<Order> allOrders = orderDao.getExecOrders(cn, cal1, cal2, sortBy);
		List<String> result = new ArrayList<>();
		try {
			for (Order o : allOrders) {
				result.add(converterToString.convert(o));
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public synchronized Integer showExecOrdersCount(Calendar cal1, Calendar cal2) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		Integer count = orderDao.getExecOrdersCount(cn, cal1, cal2);

		return count;

	}

	@Override
	public synchronized Integer showMoneyByPeriod(Calendar cal1, Calendar cal2) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		Integer sum = orderDao.getExecOrdersMoney(cn, cal1, cal2);
		return sum;
	}

	@Override
	public synchronized List<String> viewOrderDetail(String id) {
		List<String> result = null;
		Connection cn = ConnectorDB.getInstance().getConnection();
		Order order = orderDao.findEntityById(cn, id);
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
	public synchronized boolean cloneOrder(String id) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		Boolean flag = null;
		try {
			flag =  orderDao.cloneOrder(cn, id);

		} catch (SQLException e) {
			log.error(e.getMessage());
			flag = false;
		}
		return flag;
		
	}
}
