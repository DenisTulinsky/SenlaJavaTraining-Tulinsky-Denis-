package com.senla.training.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.dao.BookDAO;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.model.Book;



public class BookService implements IBookService {

	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(BookService.class);
	private BookDAO bookDao;

	public BookService(IConverterReadableString converterToString, BookDAO bookDao) {
		this.converterToString = converterToString;
		this.bookDao = bookDao;
	}

	@Override
	public synchronized boolean addBook(Book book) {
		 Session session = HibernateUtil.getSession();
		return bookDao.add(session, book);

	}

	@Override
	public synchronized boolean removeBook(Integer id) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		return bookDao.updateStatus(cn, id, Status.NOTINSTOCK.toString());

	}

	@Override
	public synchronized List<String> showAllBooks(String sortBy) {

		Connection cn = ConnectorDB.getInstance().getConnection();
		List<Book> allBooks = bookDao.findAllLazy(cn, sortBy);
		List<String> result = new ArrayList<>();
		for (Book b : allBooks) {
			result.add(converterToString.convert(b));
		}
		return result;
	}

	public synchronized List<String> viewBookDescription(Integer id) {
		List<String> result = null;
		Connection cn = ConnectorDB.getInstance().getConnection();
		Book book = bookDao.findEntityById(cn, id);

		try {
			result = AnnotationsWorker.createAnnotation(book);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public synchronized List<String> showUnwantedBooks(String sortBy) {
		Connection cn = ConnectorDB.getInstance().getConnection();
		List<Book> allBooks = bookDao.getUnwanted(cn, sortBy);
		List<String> result = new ArrayList<>();
		for (Book b : allBooks) {
			result.add(converterToString.convert(b));
		}
		return result;

	}

}