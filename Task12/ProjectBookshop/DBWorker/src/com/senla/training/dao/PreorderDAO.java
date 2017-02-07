package com.senla.training.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.senla.training.model.Preorder;
import com.senla.training.tools.DBDataParser;

public class PreorderDAO extends AbstractDAO<Preorder> {
	
	private final Logger log = Logger.getLogger(PreorderDAO.class);
	private static final String INSERT_INTO_PREORDERS_VALUES = "INSERT INTO preorders VALUES(?,?,?)";
	private static final String SELECT_FROM_PREORDERS_JOIN_BOOKS_ON_PREORDERS_BOOK_ID_BOOKS_ID_WHERE_BOOKS_ID = "SELECT * FROM preorders join books on preorders.book_id=books.id where books.id = ?";
	private static final String UPDATE_PREORDERS_SET_STATUS_WHERE_BOOK_ID = "UPDATE preorders SET status= ? WHERE book_id = ?";
	private static final String SELECT_PREORDERS = "select preorders.id, preorders.status, books.id, books.title, books.author, count(*) as count from preorders join books on books.id=preorders.book_id group by books.id order by ";

	@Override
	public PreparedStatement setInsertValues(PreparedStatement ps, Preorder preorder) {
		try {
			ps.setString(1, preorder.getId());
			ps.setString(2, preorder.getBookId());
			ps.setString(3, preorder.getStatus().toString());

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return ps;
	}

	@Override
	public String getQueryAdd() {
		return INSERT_INTO_PREORDERS_VALUES;
	}

	@Override
	public Preorder createObject(ResultSet resultSet) {
		Preorder preorder = null;
		try {
			preorder = DBDataParser.createPreorder(resultSet);

		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return preorder;
	}

	@Override
	public Preorder createObjectLazyInit(ResultSet resultSet) {
		return createObject(resultSet);
	}

	@Override
	public String getQueryUpdateStatus() {
		return UPDATE_PREORDERS_SET_STATUS_WHERE_BOOK_ID;
	}

	@Override
	public String getQueryAll() {
		return SELECT_PREORDERS;
	}

	@Override
	public String getQueryById() {
		return SELECT_FROM_PREORDERS_JOIN_BOOKS_ON_PREORDERS_BOOK_ID_BOOKS_ID_WHERE_BOOKS_ID;
	}

	@Override
	public String getQueryAllLazy() {
		return getQueryAll();
	}

}
