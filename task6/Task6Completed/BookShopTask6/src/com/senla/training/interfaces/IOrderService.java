package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public interface IOrderService {
	public void addOrder(IOrder order, String title);

	public void cancelOrder(String booktitle, String customer);

	public void executeOrder(String booktitle, String customer);

	public List<String> showExecOrdersByPeriod(Calendar cal3, Calendar cal4, Comparator<IOrder> com);

	public List<String> showAllOrders(Comparator<IOrder> com);

	public Integer showExecOrdersCount(Calendar cal3, Calendar cal4);

	public Integer showMoneyByPeriod(Calendar c1, Calendar c5);

	public List<String> viewOrderDetail(String bookstitle, String customer);

	public void cloneOrder(String title, String customer);

}
