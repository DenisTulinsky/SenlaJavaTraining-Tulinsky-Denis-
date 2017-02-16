package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.List;

import com.senla.training.enums.Status;
import com.senla.training.model.Order;

public interface IOrderService {
	public boolean addOrder(Order order);

	public boolean updateStatus(Integer id, Status status);

	public List<String> showExecOrdersByPeriod(Calendar cal3, Calendar cal4, String sortBy);

	public List<String> showAllOrders(String sortBy);

	public Long showExecOrdersCount(Calendar cal3, Calendar cal4);

	public Long showMoneyByPeriod(Calendar c1, Calendar c5);

	public List<String> viewOrderDetail(Integer id);

	public boolean cloneOrder(Integer id);

	public Boolean deleteOrder(Integer id);

}
