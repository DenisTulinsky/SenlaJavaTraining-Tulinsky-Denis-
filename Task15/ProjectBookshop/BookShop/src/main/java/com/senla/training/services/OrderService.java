package com.senla.training.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.dao.BookDAO;
import com.senla.training.dao.OrderDAO;
import com.senla.training.exceptions.MyException;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.interfaces.IOrderDAO;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.model.Order;
import com.senla.training.tools.CSVUtility;
import com.senla.training.tools.ConverterCSV;

public class OrderService implements IOrderService {

	private IOrderDAO orderDao;
	private IBookDAO bookDao;
	private static final String RESOURCES_ORDERS_CSV = "src/main/resources/Orders.csv";
	private static final String ID = "id";

	public OrderService(OrderDAO orderDao, BookDAO bookDao) {
		this.orderDao = orderDao;
		this.bookDao = bookDao;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	public void writeOrdersToCsv() throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Order> allOrders = orderDao.findAll(session, ID);
			List<String> strOrders = new ArrayList<String>();
			for (Order order : allOrders) {
				strOrders.add(ConverterCSV.orderToString(order));
			}
			CSVUtility.writeToCsv(strOrders, RESOURCES_ORDERS_CSV);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("writeOrdersToCsv failed");
		}
	}

	@Override
	public void readOrdersFromCsv() throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<String> strOrders = CSVUtility.readFromCsv(RESOURCES_ORDERS_CSV);
			for (String o : strOrders) {
				Order order = (Order) ConverterCSV.stringToOrder(bookDao, session, o);
				orderDao.add(session, order);
			}
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("readOrdersFromCsv failed");
		}
	}

	@Override
	public synchronized void addOrder(Order order) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			orderDao.add(session, order);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("addOrder failed");
		}
	}

	@Override
	public synchronized void updateOrder(Order order) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			orderDao.update(session, order);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("updateOrder failed");
		}
	}

	@Override
	public synchronized List<Order> showAllOrders(String sortBy) throws MyException {
		Transaction trans = null;
		List<Order> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			result = orderDao.findAll(session, sortBy);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new MyException("showAllOrders failed");
		}
		return result;
	}

	@Override
	public synchronized List<Order> showExecOrdersByPeriod(String startDate, String endDate, String sortBy) throws MyException {
		Transaction trans = null;
		List<Order> result = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			result = orderDao.getExecOrders(session, format.parse(startDate), format.parse(endDate), sortBy);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new MyException("showExecOrdersByPeriod failed");
		}
		return result;
	}

	public synchronized Map<String,Long> showExecOrdersCount(String startDate, String endDate) throws MyException {
		Transaction trans = null;
		Long count = null;
		Map<String,Long> map = new HashMap<String,Long>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			count = orderDao.getExecOrdersCount(session, format.parse(startDate), format.parse(endDate));
			map.put("ordersCount", count);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new MyException("showExecOrdersCount failed");
		}
		return map;
	}

	@Override
	public synchronized Map<String,Long> showMoneyByPeriod(String startDate, String endDate) throws MyException {
		Transaction trans = null;
		Long sum = null;
		Map<String,Long> map = new HashMap<String,Long>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			sum = orderDao.getExecOrdersMoney(session, format.parse(startDate), format.parse(endDate));
			map.put("moneyEarned", sum);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new MyException("showMoneyByPeriod failed");
		}
		return map;
	}

	@Override
	public synchronized Order viewOrderDetail(Integer id) throws MyException {
		Transaction trans = null;
		Order order = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			order = orderDao.findEntityById(session, id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new MyException("viewOrderDetail failed");
		}
		return order;
	}

	@Override
	public synchronized void cloneOrder(Order order) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			orderDao.add(session, order.clone());
			trans.commit();
		} catch (HibernateException | CloneNotSupportedException e) {
			trans.rollback();
			throw new MyException("cloneOrder failed");
		}
	}

	@Override
	public void deleteOrder(Integer id) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			orderDao.delete(session, id);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("deleteOrder failed");
		}
	}
}
