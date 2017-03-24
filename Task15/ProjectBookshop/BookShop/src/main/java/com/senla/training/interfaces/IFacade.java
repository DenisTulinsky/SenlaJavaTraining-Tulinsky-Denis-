package com.senla.training.interfaces;

import java.util.List;
import java.util.Map;

import com.senla.training.exceptions.MyException;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public interface IFacade {
	public void init();

	public void addBook(Book book) throws MyException;

	public void updateBook(Book book) throws MyException;

	public void writeBooksToCsv() throws MyException;

	public void writeOrdersToCsv() throws MyException;

	public Boolean writePreordersToCsv() throws MyException;

	public void readBooksFromCsv() throws MyException;

	public void readOrdersFromCsv() throws MyException;

	public Boolean readPreordersFromCsv();

	public Boolean addPreorder(Preorder preorder);

	public void addOrder(Order order) throws MyException;

	public void updateOrder(Order order) throws MyException;

	public List<Order> showExecOrdersByPeriod(String startDate, String endDate, String criterion) throws MyException;

	public List<Order> showOrders(String criterion) throws MyException;

	public Map<String, Long> showExecOrdersCount(String startDate, String endDate) throws MyException;

	public Map<String, Long> showMoneyByPeriod(String startDate, String endDate) throws MyException;

	public Book viewBookDescription(Integer id) throws MyException;

	public Order viewOrderDetail(Integer id) throws MyException;

	public List<Book> showUnwantedBooks(String criterion) throws MyException;

	public List<Preorder> showPreorders(String criterion);

	public void cloneOrder(Order order) throws MyException;

	public void deleteBook(Integer id) throws MyException;

	public void deleteOrder(Integer id) throws MyException;

	public List<Book> showAllBooks(String criterion) throws MyException;
}
