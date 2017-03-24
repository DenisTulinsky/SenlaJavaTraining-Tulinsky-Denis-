package com.senla.training.interfaces;

import java.util.List;
import java.util.Map;

import com.senla.training.exceptions.MyException;
import com.senla.training.model.Order;

public interface IOrderService {
	public void addOrder(Order order) throws MyException;

	public void updateOrder(Order order) throws MyException;

	public List<Order> showExecOrdersByPeriod(String startDate, String endDate, String sortBy) throws MyException;

	public List<Order> showAllOrders(String sortBy) throws MyException;

	public Map<String, Long> showExecOrdersCount(String startDate, String endDate) throws MyException;

	public Map<String, Long> showMoneyByPeriod(String startDate, String endDate) throws MyException;

	public Order viewOrderDetail(Integer id) throws MyException;

	public void cloneOrder(Order order) throws MyException;

	public void deleteOrder(Integer id) throws MyException;

	public void writeOrdersToCsv() throws MyException;

	public void readOrdersFromCsv() throws MyException;

}
