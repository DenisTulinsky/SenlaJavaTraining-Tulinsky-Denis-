package com.senla.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.model.Book;
import com.senla.training.properties.PropertyFactory;
import com.senla.training.tools.DBDataParser;

public class BookDAO extends AbstractDAO<Book> {
	
	private final Logger log = Logger.getLogger(BookDAO.class);
	private static final String SELECT_FROM_BOOKS_WHERE_ARRIVAL_DATE_DATE_SUB_CURDATE_INTERVAL_MONTH_ORDER_BY = "SELECT * FROM books where  arrival_date <= DATE_SUB(curdate(), INTERVAL ? MONTH) order by ";
	private static final String INSERT_INTO_BOOKS_VALUES = "INSERT INTO books VALUES(?,?,?,?,?,?,?,?)";
	private static final String SELECT_FROM_BOOKS_ORDER_BY = "select * from books order by ";
	private static final String UPDATE_BOOKS_SET_STATUS_WHERE_ID = "UPDATE books SET status=? WHERE id = ?";
	private static final String SELECT_FROM_BOOKS_WHERE_ID = "select * from books where id = ?";

	public String getQueryUpdateStatus() {
		return UPDATE_BOOKS_SET_STATUS_WHERE_ID;
	}
	
	@Override
	public String getQueryAllLazy() {
		return getQueryAll();
	}
	public String getQueryAll() {
		return (SELECT_FROM_BOOKS_ORDER_BY );
	}

	public String getQueryById() {
		return SELECT_FROM_BOOKS_WHERE_ID;
	}

	public String getQueryAdd() {
		return INSERT_INTO_BOOKS_VALUES;
	}
	
	public PreparedStatement setInsertValues(PreparedStatement ps, Book book) {
		try {
			ps.setString(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			Timestamp timestamp1 = new Timestamp (book.getPublishedDate().getTime());
			ps.setTimestamp(4, timestamp1 );
			ps.setString(5, book.getInStock().toString());
			ps.setInt(6, book.getPrice());
			Timestamp timestamp2 = new Timestamp (book.getArrivalDate().getTime());
			ps.setTimestamp(7, timestamp2);
			ps.setString(8, book.getDescription());

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return ps;
	}

	public Book createObjectLazyInit(ResultSet resultSet) {
		return createObject(resultSet);
	}
	
	public Book createObject(ResultSet resultSet) {
		Book book = null;
		try {
			book = DBDataParser.createBook(resultSet);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return book;
	}
	
	
	public List<Book> getUnwanted(Connection cn, String sortBy) {
		List<Book> listObjects = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(SELECT_FROM_BOOKS_WHERE_ARRIVAL_DATE_DATE_SUB_CURDATE_INTERVAL_MONTH_ORDER_BY +sortBy);
			ps.setInt(1, Integer.valueOf(PropertyFactory.getProps().getValue("monthsUnwanted")));
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Book book = createObject(resultSet);
				listObjects.add(book);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return listObjects;
	}

	
}
