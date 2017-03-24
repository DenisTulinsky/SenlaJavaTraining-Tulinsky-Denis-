package com.senla.training.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.dao.BookDAO;
import com.senla.training.exceptions.MyException;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookService;
import com.senla.training.model.Book;
import com.senla.training.tools.CSVUtility;
import com.senla.training.tools.ConverterCSV;

public class BookService implements IBookService {

	private BookDAO bookDao;
	private static final String RESOURCES_BOOKS_CSV = "src/main/resources/Books.csv";
	private static final String ID = "id";

	public BookService(BookDAO bookDao) {
		this.bookDao = bookDao;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public void writeBooksToCsv() throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Book> allBooks = bookDao.findAll(session, ID);
			List<String> strBooks = new ArrayList<String>();
			for (Book book : allBooks) {
				strBooks.add(ConverterCSV.bookToString(book));
			}
			CSVUtility.writeToCsv(strBooks, RESOURCES_BOOKS_CSV);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("writeBooksToCsv failed");
		}
	}

	@Override
	public void readBooksFromCsv() throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<String> strBooks = CSVUtility.readFromCsv(RESOURCES_BOOKS_CSV);
			for (String str : strBooks) {
				Book book = (Book) ConverterCSV.stringToBook(str);
				bookDao.add(session, book);
			}
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("readBooksFromCsv failed");
		}
	}

	@Override
	public synchronized void addBook(Book book) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			bookDao.add(session, book);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("addBook failed");
		}
	}

	@Override
	public synchronized void updateBook(Book book) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			bookDao.update(session, book);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("updateBook failed");
		}
	}

	@Override
	public synchronized List<Book> showAllBooks(String sortBy) throws MyException {
		Transaction trans = null;
		List<Book> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			result = bookDao.findAll(session, sortBy);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("showAllBooks failed");
		}
		return result;
	}

	public synchronized Book viewBookDescription(Integer id) throws MyException {
		Transaction trans = null;
		Book book = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			book = bookDao.findEntityById(session, id);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new MyException("viewBookDescription failed");
		}
		return book;
	}

	public synchronized List<Book> showUnwantedBooks(String sortBy) throws MyException {
		Transaction trans = null;
		List<Book> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			result = bookDao.getUnwanted(session, sortBy);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("showUnwantedBooks failed");
		}
		return result;

	}

	@Override
	public void deleteBook(Integer id) throws MyException {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			bookDao.delete(session, id);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			throw new MyException("deleteBook failed");
		}
	}
}