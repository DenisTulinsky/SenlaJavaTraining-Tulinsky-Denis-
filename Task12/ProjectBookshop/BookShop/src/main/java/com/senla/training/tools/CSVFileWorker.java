package com.senla.training.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.interfaces.ICSVFileWorker;
import com.senla.training.interfaces.IConverter;
import com.senla.training.interfaces.IOrderDAO;
import com.senla.training.interfaces.IPreorderDAO;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class CSVFileWorker implements ICSVFileWorker {

	private static final String RESOURCES_PREORDERS_CSV = "src/main/resources/Preorders.csv";
	private static final String RESOURCES_ORDERS_CSV = "src/main/resources/Orders.csv";
	private static final String RESOURCES_BOOKS_CSV = "src/main/resources/Books.csv";
	private static final String ID = "id";
	private IConverter converter;
	private final Logger log = Logger.getLogger(CSVFileWorker.class);

	public CSVFileWorker(IConverter converter) {
		this.converter = converter;
	}

	private Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public boolean writeBooksToCsv(IBookDAO bookDao) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Book> allBooks = bookDao.findAll(session, ID);
			List<String> strBooks = new ArrayList<String>();
			for (Book book : allBooks) {
				strBooks.add(converter.bookToString(book));
			}
			CSVUtility.writeToCsv(strBooks, RESOURCES_BOOKS_CSV);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean writeOrdersToCsv(IOrderDAO orderDao) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Order> allOrders = orderDao.findAll(session, ID);
			List<String> strOrders = new ArrayList<String>();
			for (Order order : allOrders) {
				strOrders.add(converter.orderToString(order));
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

	public boolean writePreordersToCsv(IPreorderDAO preorderDao) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Preorder> allPreorders = preorderDao.findAll(session, ID);
			List<String> strPreorders = new ArrayList<String>();
			for (Preorder preorder : allPreorders) {
				strPreorders.add(converter.preorderToString(preorder));
			}
			CSVUtility.writeToCsv(strPreorders, RESOURCES_PREORDERS_CSV);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean readBooksFromCsv(IBookDAO bookDao) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<String> strBooks = CSVUtility.readFromCsv(RESOURCES_BOOKS_CSV);
			for (String str : strBooks) {
				Book book = (Book) converter.stringToBook(str);
				bookDao.add(session, book);
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
	public boolean readPreordersFromCsv(IPreorderDAO preorderDao) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<String> strPreorders = CSVUtility.readFromCsv(RESOURCES_PREORDERS_CSV);
			for (String str : strPreorders) {
				Preorder preorder = (Preorder) converter.stringToPreorder(session, str);
				preorderDao.add(session, preorder);
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
	public boolean readOrdersFromCsv(IOrderDAO orderDao) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<String> strOrders = CSVUtility.readFromCsv(RESOURCES_ORDERS_CSV);
			for (String o : strOrders) {
				Order order = (Order) converter.stringToOrder(session, o);
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
}
