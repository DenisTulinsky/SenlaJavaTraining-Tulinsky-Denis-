package com.senla.training.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.senla.training.annotationsWorker.AnnotationsWorker;
import com.senla.training.connection.ConnectorDB;
import com.senla.training.dao.BookDAO;
import com.senla.training.interfaces.IBook;
import com.senla.training.interfaces.IBookService;
import com.senla.training.interfaces.IConverterReadableString;
import com.senla.training.interfaces.IStorage;
import com.senla.training.model.Book;
import com.senla.training.properties.PropertyFactory;
import com.senla.training.tools.Status;

/**
 * Provides instruments for working with books and the storage.
 * 
 * @author Denis
 *
 */
public class BookService implements IBookService {

	private IStorage storage;
	private IConverterReadableString converterToString;
	private final Logger log = Logger.getLogger(BookService.class);

	public BookService(IStorage storage, IConverterReadableString converterToString) {
		this.storage = storage;
		this.converterToString = converterToString;

	}

	@Override
	public synchronized void addBook(Book book) {
		book.setId(UUID.randomUUID().toString());
		Connection cn = ConnectorDB.getInstance().getConnection();
		BookDAO bookDao = new BookDAO(cn);
		bookDao.add(book);
				
	}

	@Override
	public synchronized void removeBook(String id) {
		
		Connection cn = ConnectorDB.getInstance().getConnection();
		BookDAO bookDao = new BookDAO(cn);
		bookDao.updateStatus(id, Status.NOTINSTOCK.toString());
		
	}
		

	@Override
	public synchronized List<String> showAllBooks(String sortBy) {

		Connection cn = ConnectorDB.getInstance().getConnection();
		BookDAO bookDao = new BookDAO(cn);
		List<Book> allBooks = bookDao.findAll(sortBy);

		List<String> result = new ArrayList<>();
		for (IBook b : allBooks) {
			result.add(converterToString.convert(b));
		}
		return result;
	}

	public synchronized List<String> viewBookDescription(String id) {
		List<String> result = null;

		Connection cn = ConnectorDB.getInstance().getConnection();
		BookDAO bookDao = new BookDAO(cn);
		Book book = bookDao.findEntityById(id);

		try {
			result = AnnotationsWorker.createAnnotation(book);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public synchronized List<String> showUnwantedBooks(Comparator<IBook> com) {
		List<IBook> allBooks = storage.getAllBooks();
		List<IBook> unwantedBooks = new ArrayList<>();
		List<String> result = new ArrayList<>();
		try {
			for (IBook book : allBooks) {
				if (book.getInStock() == Status.INSTOCK) {
					Calendar arrDate = new GregorianCalendar();
					arrDate.setTime(book.getArrivalDate());
					Calendar date = Calendar.getInstance();

					Integer mm = Integer.valueOf(PropertyFactory.getProps().getValue("monthsUnwanted"));
					date.add(Calendar.MONTH, -mm);

					if (arrDate.before(date)) {
						unwantedBooks.add(book);
					}
				}
			}
			Collections.sort(unwantedBooks, com);
			for (IBook book : unwantedBooks) {
				result.add(converterToString.convert(book));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

}