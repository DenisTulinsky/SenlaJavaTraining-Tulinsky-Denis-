package com.senla.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.senla.training.model.Order;
import com.senla.training.tools.ObjectCreator;

public class OrderDAO extends AbstractDAO<Order> {

	private static final String SELECT_FROM_BOOKS_WHERE_ID = "select * from books where id = ?";
	private static final String SELECT_FROM_ORDERS_ORDER_BY = "select * from orders order by ";
	private static final String UPDATE_ORDERS_SET_STATUS_WHERE_ID = "UPDATE orders SET status=? WHERE id = ?";

	public OrderDAO(Connection connection) {
		super(connection);
		
	}

	public PreparedStatement getQueryUpdateStatus(String id, String status) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(UPDATE_ORDERS_SET_STATUS_WHERE_ID);
			ps.setString(1, status);
			ps.setString(2, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	public PreparedStatement getQueryAdd(Order order) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?,?)");
			ps.setString(1,order.getId());
			ps.setString(2,order.getBook().getId());
			ps.setString(3,order.getCustomer());
			ps.setInt(4,order.getPrice());
			ps.setLong(5,order.getExecutionDate().getTime());
			ps.setString(6,order.getStatus().toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	public Order createObject(ResultSet resultSet) {
		Order order = null;
		try {
			order = ObjectCreator.createOrder(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return order;
	}

	@Override
	public PreparedStatement getQueryAll(String sortBy) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SELECT_FROM_ORDERS_ORDER_BY + sortBy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
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

}
