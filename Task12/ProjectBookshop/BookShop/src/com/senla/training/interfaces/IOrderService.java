package com.senla.training.interfaces;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.senla.training.model.Order;

public interface IOrderService {
	public boolean addOrder(Order order);

	public boolean cancelOrder(Integer id);

	public boolean executeOrder(Integer id);

	public List<String> showExecOrdersByPeriod(Calendar cal3, Calendar cal4, String sortBy);

	public List<String> showAllOrders(String sortBy);

	public Integer showExecOrdersCount(Calendar cal3, Calendar cal4);

	public Integer showMoneyByPeriod(Calendar c1, Calendar c5);

	public List<String> viewOrderDetail(Integer id);

	public boolean cloneOrder(Integer id) throws SQLException;

}
