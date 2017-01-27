package com.senla.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.senla.training.model.Book;
import com.senla.training.tools.ObjectCreator;

public class BookDAO extends AbstractDAO<Book> {

	private static final String INSERT_INTO_BOOKS_VALUES = "INSERT INTO books VALUES(?,?,?,?,?,?,?,?)";
	private static final String SELECT_FROM_BOOKS_ORDER_BY = "select * from books order by ";
	private static final String UPDATE_BOOKS_SET_STATUS_WHERE_ID = "UPDATE books SET status=? WHERE id = ?";
	private static final String SELECT_FROM_BOOKS_WHERE_ID = "select * from books where id = ?";

	public BookDAO(Connection connection) {
		super(connection);

	}

	public PreparedStatement getQueryUpdateStatus(String id, String status) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(UPDATE_BOOKS_SET_STATUS_WHERE_ID);
			ps.setString(1, status);
			ps.setString(2, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public PreparedStatement getQueryAll(String sortBy) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SELECT_FROM_BOOKS_ORDER_BY + sortBy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public PreparedStatement getQueryById(String id) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SELECT_FROM_BOOKS_WHERE_ID);
			ps.setString(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;

	}

	public PreparedStatement getQueryAdd(Book book) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(INSERT_INTO_BOOKS_VALUES);
			ps.setString(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setLong(4, book.getPublishedDate().getTime());
			ps.setString(5, book.getInStock().toString());
			ps.setInt(6, book.getPrice());
			ps.setLong(7, book.getPublishedDate().getTime());
			ps.setString(8, book.getDescription());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public Book createObject(ResultSet resultSet) {
		Book book = null;
		try {
			book = ObjectCreator.createBook(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

}
