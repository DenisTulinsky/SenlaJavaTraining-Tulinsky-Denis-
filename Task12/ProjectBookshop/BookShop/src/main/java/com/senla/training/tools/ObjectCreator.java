package com.senla.training.tools;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.enums.Status;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookDAO;
import com.senla.training.model.Book;
import com.senla.training.model.Order;
import com.senla.training.model.Preorder;

public class ObjectCreator {

	public static Preorder createPreorder(IBookDAO bookDao, Integer bookId) {
		Preorder preorder = new Preorder();
		Transaction trans = null;
		try {
			preorder.setStatus(Status.ACTIVE);
			Session session = HibernateUtil.getSession();
			trans = session.beginTransaction();
			Book book = bookDao.findEntityById(session, bookId);
			preorder.setBook(book);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}
		return preorder;
	}

	public static Book createBook(String title, String author, GregorianCalendar publishedDate, Status inStock,
			Integer price, GregorianCalendar arrivalDate, String description) {
		Book book = new Book();
		try {
			book.setTitle(title);
			book.setAuthor(author);
			book.setPublishedDate(publishedDate.getTime());
			book.setStatus(inStock);
			book.setPrice(price);
			book.setArrivalDate(arrivalDate.getTime());
			book.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public static Order createOrder(IBookDAO bookDao, String customer, Calendar executionDate, List<Integer> ids) {
		Order order = new Order();
		Transaction trans = null;
		Set<Book> books = new HashSet<Book>();
		Integer price = 0;
		try {
			Session session = HibernateUtil.getSession();
			trans = session.beginTransaction();
			for (Integer id : ids) {
				Book book = bookDao.findEntityById(session, id);
				price += book.getPrice();
				books.add(book);
			}
			order.setBooks(books);
			order.setPrice(price);
			order.setCustomer(customer);
			order.setExecutionDate(executionDate.getTime());
			order.setStatus(Status.ACTIVE);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}
		return order;
	}
}
