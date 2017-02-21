package com.senla.training.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.dao.BookDAO;
import com.senla.training.dao.OrderDAO;
import com.senla.training.enums.Status;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IOrderDAO;
import com.senla.training.interfaces.IOrderService;
import com.senla.training.model.Order;
import com.senla.training.tools.CSVUtility;
import com.senla.training.tools.ConverterCSV;
import com.senla.training.tools.ObjectCreator;

public class OrderService implements IOrderService {

	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(OrderService.class);
	private IOrderDAO orderDao;
	private IBookDAO bookDao;
	private static final String RESOURCES_ORDERS_CSV = "src/main/resources/Orders.csv";
	private static final String ID = "id";

	public OrderService(IConverterReadableString converterToString, OrderDAO orderDao, BookDAO bookDao) {
		this.converterToString = converterToString;
		this.orderDao = orderDao;
		this.bookDao = bookDao;

	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	public boolean writeOrdersToCsv() {
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
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean readOrdersFromCsv() {
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
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized boolean addOrder(String customer, Calendar executiondate, List<Integer> ids) {
		Order order = ObjectCreator.createOrder(bookDao, customer, executiondate, ids);
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			orderDao.add(session, order);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized boolean updateStatus(Integer id, Status status) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			Order order = orderDao.findEntityById(session, id);
			order.setStatus(status);
			order.setExecutionDate(Calendar.getInstance().getTime());
			orderDao.update(session, order);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized List<String> showAllOrders(String sortBy) {
		Transaction trans = null;
		List<String> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Order> allOrders = orderDao.findAll(session, sortBy);

			result = new ArrayList<>();
			for (Order o : allOrders) {
				result.add(converterToString.convert(o));
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public synchronized List<String> showExecOrdersByPeriod(Calendar cal1, Calendar cal2, String sortBy) {
		Transaction trans = null;
		List<String> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Order> orders = orderDao.getExecOrders(session, cal1, cal2, sortBy);
			result = new ArrayList<>();
			for (Order o : orders) {
				result.add(converterToString.convert(o));
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			log.error(e.getMessage());

		}
		return result;
	}

	public synchronized Long showExecOrdersCount(Calendar cal1, Calendar cal2) {
		Transaction trans = null;
		Long count = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			count = orderDao.getExecOrdersCount(session, cal1, cal2);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return count;

	}

	@Override
	public synchronized Long showMoneyByPeriod(Calendar cal1, Calendar cal2) {
		Transaction trans = null;
		Long sum = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			sum = orderDao.getExecOrdersMoney(session, cal1, cal2);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return sum;
	}

	@Override
	public synchronized List<String> viewOrderDetail(Integer id) {
		Transaction trans = null;
		List<String> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			Order order = orderDao.findEntityById(session, id);
			result = AnnotationsWorker.createAnnotation(order);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;
	}

	@Override
	public synchronized boolean cloneOrder(Integer id) {
		Transaction trans = null;
		Order order = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			order = orderDao.findEntityById(session, id);
			orderDao.add(session, order.clone());
			trans.commit();
		} catch (HibernateException | CloneNotSupportedException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteOrder(Integer id) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			orderDao.delete(session, id);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
}
