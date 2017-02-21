package com.senla.training.services;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.dao.BookDAO;
import com.senla.training.enums.Status;
import com.senla.training.hibernateUtils.HibernateUtil;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.model.Book;
import com.senla.training.tools.CSVUtility;
import com.senla.training.tools.ConverterCSV;
import com.senla.training.tools.ObjectCreator;

public class BookService implements IBookService {

	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(BookService.class);
	private BookDAO bookDao;
	private static final String RESOURCES_BOOKS_CSV = "src/main/resources/Books.csv";
	private static final String ID = "id";

	public BookService(IConverterReadableString converterToString, BookDAO bookDao) {
		this.converterToString = converterToString;
		this.bookDao = bookDao;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public boolean writeBooksToCsv() {
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
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean readBooksFromCsv() {
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
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized boolean addBook(String title, String author, GregorianCalendar publishedDate, Status status,
			Integer price, GregorianCalendar arrivalDate, String description) {
		Book book = ObjectCreator.createBook(title, author, publishedDate, status, price, arrivalDate, description);
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			bookDao.add(session, book);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized boolean updateStatus(Integer id, Status status) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			Book book = bookDao.findEntityById(session, id);
			book.setStatus(status);
			bookDao.update(session, book);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public synchronized List<String> showAllBooks(String sortBy) {
		Transaction trans = null;
		List<String> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Book> allBooks = bookDao.findAll(session, sortBy);
			result = new ArrayList<>();
			for (Book b : allBooks) {
				result.add(converterToString.convert(b));
			}
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;
	}

	public synchronized List<String> viewBookDescription(Integer id) {
		Transaction trans = null;
		List<String> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			Book book = bookDao.findEntityById(session, id);
			result = AnnotationsWorker.createAnnotation(book);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;
	}

	public synchronized List<String> showUnwantedBooks(String sortBy) {
		Transaction trans = null;
		List<String> result = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			List<Book> books = bookDao.getUnwanted(session, sortBy);
			result = new ArrayList<>();
			for (Book b : books) {
				result.add(converterToString.convert(b));
			}
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
		}
		return result;

	}

	@Override
	public Boolean deleteBook(Integer id) {
		Transaction trans = null;
		try {
			Session session = getSession();
			trans = session.beginTransaction();
			bookDao.delete(session, id);
			trans.commit();
		} catch (HibernateException e) {
			trans.rollback();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
}