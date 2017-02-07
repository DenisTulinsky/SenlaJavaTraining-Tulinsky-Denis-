package com.senla.training.tools;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.DI.DI;
import com.senla.training.dao.BookDAO;
import com.senla.training.dao.OrderDAO;
import com.senla.training.dao.PreorderDAO;
import com.senla.training.hibernateUtils.ConnectorDB;
import com.senla.training.interfaces.ICSVFileWorker;
import com.senla.training.interfaces.ICSVUtility;
import com.senla.training.interfaces.IConverter;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class CSVFileWorker implements ICSVFileWorker {

	private IConverter converter;
	private final Logger log = Logger.getLogger(CSVFileWorker.class);

	public CSVFileWorker(IConverter converter) {
		this.converter = converter;

	}

	@Override
	public boolean writeBooksToCsv(BookDAO bookDao) {
		ICSVUtility csvUtil = (ICSVUtility) DI.load(ICSVUtility.class, "Resources/Books.csv");
		try {
			Connection cn = ConnectorDB.getInstance().getConnection();
			List<Book> allBooks = bookDao.findAll(cn, "title");
			List<String> strBooks = new ArrayList<String>();
			for (Book book : allBooks) {
				strBooks.add(converter.bookToString(book));
			}
			csvUtil.writeToCsv(strBooks);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			return false;

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;

	}

	public boolean writeOrdersToCsv(OrderDAO orderDao) {
		ICSVUtility csvUtil = (ICSVUtility) DI.load(ICSVUtility.class, "Resources/Orders.csv");
		try {

			Connection cn = ConnectorDB.getInstance().getConnection();
			List<Order> allOrders = orderDao.findAllLazy(cn, "exec_date");
			List<String> strOrders = new ArrayList<String>();
			for (Order order : allOrders) {
				strOrders.add(converter.orderToString(order));
			}
			csvUtil.writeToCsv(strOrders);

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			return false;

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean writePreordersToCsv(PreorderDAO preorderDao) {
		ICSVUtility csvUtil = (ICSVUtility) DI.load(ICSVUtility.class, "Resources/Preorders.csv");
		try {
			Connection cn = ConnectorDB.getInstance().getConnection();
			List<Preorder> allPreorders = preorderDao.findAll(cn, "title");
			List<String> strPreorders = new ArrayList<String>();
			for (Preorder preorder : allPreorders) {
				strPreorders.add(converter.preorderToString(preorder));
			}
			csvUtil.writeToCsv(strPreorders);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			return false;

		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean readBooksFromCsv(BookDAO bookDao) {
		ICSVUtility csvUtil = (ICSVUtility) DI.load(ICSVUtility.class, "Resources/Books.csv");
		Connection cn = ConnectorDB.getInstance().getConnection();
		Boolean flag = false;
		try {
			List<String> strBooks = csvUtil.readFromCsv();
			for (String str : strBooks) {
				Book book = (Book) converter.stringToBook(str);
				flag = bookDao.add(cn, book);

			}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			flag = false;

		} catch (Exception e) {
			log.error(e.getMessage());
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean readPreordersFromCsv(PreorderDAO preorderDao) {
		ICSVUtility csvUtil = (ICSVUtility) DI.load(ICSVUtility.class, "Resources/Preorders.csv");
		Connection cn = ConnectorDB.getInstance().getConnection();
		Boolean flag = false;
		try {
			List<String> strPreorders = csvUtil.readFromCsv();
			for (String str : strPreorders) {
				Preorder preorder = (Preorder) converter.stringToPreorder(str);
				flag = preorderDao.add(cn, preorder);

			}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			flag = false;
		} catch (Exception e) {
			log.error(e.getMessage());
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean readOrdersFromCsv(OrderDAO orderDao) {
		ICSVUtility csvUtil = (ICSVUtility) DI.load(ICSVUtility.class, "Resources/Orders.csv");
		Connection cn = ConnectorDB.getInstance().getConnection();
		Boolean flag = false;
		try {
			List<String> strOrders = csvUtil.readFromCsv();
			for (String str : strOrders) {
				Order order = (Order) converter.stringToOrder(str);
				flag = orderDao.add(cn, order);
			}
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			flag = false;
		} catch (Exception e) {
			log.error(e.getMessage());
			flag = false;
		}
		return flag;

	}
}
