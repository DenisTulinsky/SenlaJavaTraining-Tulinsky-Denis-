package com.senla.training.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class DBDataParser {
	public static Book createBook(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.setId(resultSet.getString("books.id"));
		book.setTitle(resultSet.getString("books.title"));
		book.setAuthor(resultSet.getString("books.author"));
		book.setPublishedDate(resultSet.getDate("books.published_date"));
		book.setInStock(Status.valueOf(resultSet.getString("books.status")));
		book.setPrice(Integer.valueOf(resultSet.getInt("books.price")));
		book.setArrivalDate(resultSet.getDate("books.arrival_date"));
		book.setDescription(resultSet.getString("books.description"));
		return book;

	}

	public static Order createOrder(ResultSet resultSet) throws SQLException {
		Order order = new Order();
		Book book = createBook(resultSet);
		order.setBook(book);
		order.setId(resultSet.getString("orders.id"));
		order.setCustomer(resultSet.getString("orders.customer"));
		order.setPrice(Integer.valueOf(resultSet.getInt("orders.price")));
		order.setExecutionDate(resultSet.getDate("orders.exec_date"));
		order.setStatus(Status.valueOf(resultSet.getString("orders.status")));
		return order;
	}
	
	public static Order createOrderLazyInit(ResultSet resultSet) throws SQLException {
		Order order = new Order();
		
		order.setBookId(resultSet.getString("orders.book_id"));
		order.setId(resultSet.getString("orders.id"));
		order.setCustomer(resultSet.getString("orders.customer"));
		order.setPrice(Integer.valueOf(resultSet.getInt("orders.price")));
		order.setExecutionDate(resultSet.getDate("orders.exec_date"));
		order.setStatus(Status.valueOf(resultSet.getString("orders.status")));
		return order;
	}
	
	public static Preorder createPreorder(ResultSet resultSet) throws SQLException {
		Preorder preorder = new Preorder();
		
		preorder.setId(resultSet.getString("preorders.id"));
		preorder.setBookId(resultSet.getString("books.id"));
		preorder.setTitle(resultSet.getString("books.title"));
		preorder.setAuthor(resultSet.getString("books.author"));
		preorder.setCount(Integer.valueOf(resultSet.getInt("count")));
		preorder.setStatus(Status.valueOf(resultSet.getString("preorders.status")));
		return preorder;
	}
}
