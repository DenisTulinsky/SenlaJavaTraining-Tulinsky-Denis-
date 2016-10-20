package com.senla.training.interfaces;

import java.util.Calendar;
import java.util.Comparator;

public interface IOrderService {
public void addOrder(String title, String author,String customer, int price, Calendar executiondate);
public void cancelOrder(String title, String author,String customer, int price, Calendar executiondate);
public void executeOrder(String title, String author,String customer, int price, Calendar executiondate);

public void showExecOrdersByPeriod(Calendar cal3, Calendar cal4, Comparator<IOrder> com);
void showAllOrders(Comparator<IOrder> com);
public void showExecOrdersCount(Calendar cal3, Calendar cal4);
public void showMoneyByPeriod(Calendar c1, Calendar c5);
public void viewOrderDetail(String title, String author);

}
