package com.senla.training.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.senla.training.connection.ConnectorDB;
import com.senla.training.dao.BookDAO;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IOrder;
import com.senla.training.interfaces.IPreorder;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ObjectCreator {

	public static IPreorder createPreorder(String title, String author) {
		return new Preorder(title, author);
	}

	public static IBook createBook(String title, String author, GregorianCalendar publishedDate, Status inStock,
			Integer price, GregorianCalendar arrivalDate, String description) {
		return new Book(title, author, publishedDate.getTime(), inStock, price, arrivalDate.getTime(), description);
	}

	public static IOrder createOrder(String customer, Calendar executiondate) {
		return new Order(customer, executiondate.getTime());
	}

	public static Book createBook(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.setId(resultSet.getString("id"));
		book.setTitle(resultSet.getString("title"));
		book.setAuthor(resultSet.getString("author"));

		Date publDate = new Date();
		publDate.setTime(Long.valueOf(resultSet.getLong("published_date")));
		book.setPublishedDate(publDate);

		book.setInStock(Status.valueOf(resultSet.getString("status")));
		book.setPrice(Integer.valueOf(resultSet.getInt("price")));

		Date arrivDate = new Date();
		arrivDate.setTime(Long.valueOf(resultSet.getLong("arrival_date")));
		book.setArrivalDate(arrivDate);
		book.setDescription(resultSet.getString("description"));
		return book;

	}

	public static Order createOrder(ResultSet resultSet) throws SQLException {
		Order order = new Order();
		order.setId(resultSet.getString("id"));
		
		Connection cn = ConnectorDB.getInstance().getConnection();
		BookDAO bookDao = new BookDAO(cn);
		Book book = bookDao.findEntityById(resultSet.getString("book_id"));
		order.setBook(book);

		order.setCustomer(resultSet.getString("customer"));
		order.setPrice(Integer.valueOf(resultSet.getInt("price")));

		Date execDate = new Date();
		execDate.setTime(Long.valueOf(resultSet.getLong("exec_date")));
		order.setExecutionDate(execDate);
		order.setStatus(Status.valueOf(resultSet.getString("status")));
		return order;
	}

}
