package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import com.senla.training.model.Order;

public interface IOrderService {
	public void addOrder(Order order, String title);

	public void cancelOrder(String id);

	public void executeOrder(String id);

	public List<String> showExecOrdersByPeriod(Calendar cal3, Calendar cal4, Comparator<IOrder> com);

	public List<String> showAllOrders(String sortBy);

	public Integer showExecOrdersCount(Calendar cal3, Calendar cal4);

	public Integer showMoneyByPeriod(Calendar c1, Calendar c5);

	public List<String> viewOrderDetail(String id);

	public void cloneOrder(String title, String customer);


}
