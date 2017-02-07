package com.senla.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.senla.training.model.Order;
import com.senla.training.tools.DBDataParser;

public class OrderDAO extends AbstractDAO<Order> {

	private static final String INSERT_INTO_ORDERS_ID_BOOK_ID_CUSTOMER_PRICE_EXEC_DATE_STATUS_SELECT_BOOK_ID_CUSTOMER_PRICE_EXEC_DATE_STATUS_FROM_ORDERS_WHERE_ID = "INSERT INTO orders (id,book_id, customer, price, exec_date, status) SELECT ?, book_id, customer, price, exec_date, status FROM orders WHERE id = ?";
	private static final String SELECT_SUM_PRICE_FROM_ORDERS_WHERE_STATUS_EXECUTED_AND_EXEC_DATE_BETWEEN_AND = "SELECT sum(price) FROM orders where status = 'EXECUTED' and exec_date between ? and ?";
	private final Logger log = Logger.getLogger(OrderDAO.class);
	private static final String SELECT_FROM_ORDERS_JOIN_BOOKS_ON_BOOKS_ID_ORDERS_BOOK_ID_ORDER_BY = "select * from orders join books on books.id=orders.book_id order by ";
	private static final String SELECT_COUNT_FROM_ORDERS_WHERE_STATUS_EXECUTED_AND_EXEC_DATE_BETWEEN_AND = "SELECT count(*) FROM orders  where status = 'EXECUTED' and exec_date between ? and ?";
	private static final String SELECT_FROM_ORDERS_WHERE_STATUS_EXECUTED_AND_EXEC_DATE_BETWEEN_AND_ORDER_BY = "SELECT * FROM orders where status = 'EXECUTED' and exec_date between ? and ? order by ";
	private static final String INSERT_INTO_ORDERS_VALUES = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
	private static final String SELECT_FROM_ORDERS_WHERE_ID = "SELECT * FROM orders join books on orders.book_id=books.id where orders.id = ?";
	private static final String SELECT_FROM_ORDERS_ORDER_BY = "select * from orders order by ";
	private static final String UPDATE_ORDERS_SET_STATUS_WHERE_ID = "UPDATE orders SET status=? WHERE id = ?";

	public String getQueryUpdateStatus() {
		return UPDATE_ORDERS_SET_STATUS_WHERE_ID;
	}

	@Override
	public String getQueryAllLazy() {
		return SELECT_FROM_ORDERS_ORDER_BY;
	}

	@Override
	public String getQueryAll() {
		return SELECT_FROM_ORDERS_JOIN_BOOKS_ON_BOOKS_ID_ORDERS_BOOK_ID_ORDER_BY;
	}

	@Override
	public String getQueryById() {
		return SELECT_FROM_ORDERS_WHERE_ID;
	}

	@Override
	public String getQueryAdd() {
		return INSERT_INTO_ORDERS_VALUES;
	}

	public PreparedStatement setInsertValues(PreparedStatement ps, Order order) {
		try {
			ps.setString(1, order.getId());
			ps.setString(2, order.getBookId());
			ps.setString(3, order.getCustomer());
			ps.setInt(4, order.getPrice());
			Timestamp timestamp = new Timestamp(order.getExecutionDate().getTime());
			ps.setTimestamp(5, timestamp);
			ps.setString(6, order.getStatus().toString());

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return ps;
	}

	@Override
	public Order createObjectLazyInit(ResultSet resultSet) {
		Order order = null;
		try {
			order = DBDataParser.createOrderLazyInit(resultSet);

		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return order;
	}

	@Override
	public Order createObject(ResultSet resultSet) {
		Order order = null;
		try {
			order = DBDataParser.createOrder(resultSet);

		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return order;
	}

	public List<Order> getExecOrders(Connection cn, Calendar cal1, Calendar cal2, String sortBy) {
		List<Order> listObjects = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(
					SELECT_FROM_ORDERS_WHERE_STATUS_EXECUTED_AND_EXEC_DATE_BETWEEN_AND_ORDER_BY + sortBy);

			Timestamp timestamp1 = new Timestamp(cal1.getTime().getTime());
			ps.setTimestamp(1, timestamp1);

			Timestamp timestamp2 = new Timestamp(cal2.getTime().getTime());
			ps.setTimestamp(2, timestamp2);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Order order = createObjectLazyInit(resultSet);
				listObjects.add(order);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return listObjects;
	}

	public Integer getExecOrdersCount(Connection cn, Calendar cal1, Calendar cal2) {
		PreparedStatement ps = null;
		Integer count = null;
		try {
			ps = cn.prepareStatement(SELECT_COUNT_FROM_ORDERS_WHERE_STATUS_EXECUTED_AND_EXEC_DATE_BETWEEN_AND);

			
			ps.setTimestamp(1, new Timestamp(cal1.getTime().getTime()));
			ps.setTimestamp(2, new Timestamp(cal2.getTime().getTime()));

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return count;
	}

	public Integer getExecOrdersMoney(Connection cn, Calendar cal1, Calendar cal2) {
		PreparedStatement ps = null;
		Integer sum = null;
		try {
			ps = cn.prepareStatement(SELECT_SUM_PRICE_FROM_ORDERS_WHERE_STATUS_EXECUTED_AND_EXEC_DATE_BETWEEN_AND);

			Timestamp timestamp1 = new Timestamp(cal1.getTime().getTime());
			ps.setTimestamp(1, timestamp1);
			Timestamp timestamp2 = new Timestamp(cal2.getTime().getTime());
			ps.setTimestamp(2, timestamp2);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				sum = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return sum;
	}

	public boolean cloneOrder(Connection cn, String orderId) throws SQLException {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(
					INSERT_INTO_ORDERS_ID_BOOK_ID_CUSTOMER_PRICE_EXEC_DATE_STATUS_SELECT_BOOK_ID_CUSTOMER_PRICE_EXEC_DATE_STATUS_FROM_ORDERS_WHERE_ID);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, orderId);
			Integer row = ps.executeUpdate();
			if (row.equals(1)) {
				flag = true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			close(ps);
		}
		return flag;

	}
}
